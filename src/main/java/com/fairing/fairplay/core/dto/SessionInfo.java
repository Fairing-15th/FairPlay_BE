package com.fairing.fairplay.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionInfo {
    
    private Long userId;
    private String email;
    private String name;
    private String roleName;
    private Integer roleId;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime lastAccessedAt;
    private String userAgent;
    private String ipAddress;
    
    /**
     * 세션 정보 업데이트 (마지막 접근 시간 등)
     */
    public SessionInfo updateLastAccessed() {
        this.lastAccessedAt = LocalDateTime.now();
        return this;
    }
    
    /**
     * 세션 생성을 위한 정적 팩토리 메서드
     */
    public static SessionInfo create(Long userId, String email, String name, 
                                   String roleName, Integer roleId, String phone,
                                   String userAgent, String ipAddress) {
        LocalDateTime now = LocalDateTime.now();
        return SessionInfo.builder()
                .userId(userId)
                .email(email)
                .name(name)
                .roleName(roleName)
                .roleId(roleId)
                .phone(phone)
                .createdAt(now)
                .lastAccessedAt(now)
                .userAgent(userAgent)
                .ipAddress(ipAddress)
                .build();
    }
}