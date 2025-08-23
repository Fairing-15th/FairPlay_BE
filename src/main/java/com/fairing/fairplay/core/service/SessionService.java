package com.fairing.fairplay.core.service;

import com.fairing.fairplay.core.dto.SessionInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionService {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    @Value("${session.timeout-minutes:30}")
    private int sessionTimeoutMinutes;

    @Value("${session.prefix:session:}")
    private String sessionPrefix;

    /**
     * 새로운 세션 생성 및 Redis에 저장
     */
    public String createSession(SessionInfo sessionInfo) {
        String sessionId = UUID.randomUUID().toString();
        String sessionKey = sessionPrefix + sessionId;
        
        try {
            String sessionData = objectMapper.writeValueAsString(sessionInfo);
            redisTemplate.opsForValue().set(
                sessionKey, 
                sessionData, 
                sessionTimeoutMinutes, 
                TimeUnit.MINUTES
            );
            
            log.debug("Session created: {} for user: {}", sessionId, sessionInfo.getUserId());
            return sessionId;
            
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize session info for user: {}", sessionInfo.getUserId(), e);
            throw new RuntimeException("세션 생성 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 세션 정보 조회
     */
    public SessionInfo getSession(String sessionId) {
        if (sessionId == null || sessionId.trim().isEmpty()) {
            return null;
        }
        
        String sessionKey = sessionPrefix + sessionId;
        String sessionData = redisTemplate.opsForValue().get(sessionKey);
        
        if (sessionData == null) {
            log.debug("Session not found or expired: {}", sessionId);
            return null;
        }
        
        try {
            SessionInfo sessionInfo = objectMapper.readValue(sessionData, SessionInfo.class);
            // 세션 사용 시 TTL 연장 (sliding session)
            extendSession(sessionId);
            return sessionInfo;
            
        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize session data for session: {}", sessionId, e);
            // 잘못된 세션 데이터는 삭제
            deleteSession(sessionId);
            return null;
        }
    }

    /**
     * 세션 TTL 연장 (사용자가 활동할 때마다 호출)
     */
    public void extendSession(String sessionId) {
        if (sessionId == null || sessionId.trim().isEmpty()) {
            return;
        }
        
        String sessionKey = sessionPrefix + sessionId;
        Boolean exists = redisTemplate.hasKey(sessionKey);
        
        if (Boolean.TRUE.equals(exists)) {
            redisTemplate.expire(sessionKey, sessionTimeoutMinutes, TimeUnit.MINUTES);
            log.debug("Session extended: {}", sessionId);
        }
    }

    /**
     * 세션 정보 업데이트
     */
    public void updateSession(String sessionId, SessionInfo sessionInfo) {
        if (sessionId == null || sessionId.trim().isEmpty()) {
            return;
        }
        
        String sessionKey = sessionPrefix + sessionId;
        
        try {
            String sessionData = objectMapper.writeValueAsString(sessionInfo);
            // 기존 TTL 유지하면서 데이터만 업데이트
            Long ttl = redisTemplate.getExpire(sessionKey, TimeUnit.SECONDS);
            if (ttl != null && ttl > 0) {
                redisTemplate.opsForValue().set(sessionKey, sessionData, ttl, TimeUnit.SECONDS);
                log.debug("Session updated: {} for user: {}", sessionId, sessionInfo.getUserId());
            } else {
                // TTL이 없거나 만료된 경우 새로운 타임아웃으로 설정
                redisTemplate.opsForValue().set(sessionKey, sessionData, sessionTimeoutMinutes, TimeUnit.MINUTES);
                log.debug("Session recreated with new TTL: {} for user: {}", sessionId, sessionInfo.getUserId());
            }
            
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize session info for update: {}", sessionId, e);
            throw new RuntimeException("세션 업데이트 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 세션 삭제 (로그아웃 등)
     */
    public void deleteSession(String sessionId) {
        if (sessionId == null || sessionId.trim().isEmpty()) {
            return;
        }
        
        String sessionKey = sessionPrefix + sessionId;
        Boolean deleted = redisTemplate.delete(sessionKey);
        
        if (Boolean.TRUE.equals(deleted)) {
            log.debug("Session deleted: {}", sessionId);
        } else {
            log.debug("Session not found for deletion: {}", sessionId);
        }
    }

    /**
     * 특정 사용자의 모든 세션 삭제 (다중 로그인 차단 등)
     */
    public void deleteAllUserSessions(Long userId) {
        String pattern = sessionPrefix + "*";
        redisTemplate.keys(pattern).forEach(key -> {
            String sessionData = redisTemplate.opsForValue().get(key);
            if (sessionData != null) {
                try {
                    SessionInfo sessionInfo = objectMapper.readValue(sessionData, SessionInfo.class);
                    if (userId.equals(sessionInfo.getUserId())) {
                        redisTemplate.delete(key);
                        log.debug("Deleted session for user {}: {}", userId, key);
                    }
                } catch (JsonProcessingException e) {
                    log.error("Failed to parse session data during cleanup for key: {}", key, e);
                    // 파싱 실패한 세션은 삭제
                    redisTemplate.delete(key);
                }
            }
        });
    }

    /**
     * 세션 유효성 검증
     */
    public boolean isValidSession(String sessionId) {
        return getSession(sessionId) != null;
    }
}