package com.fairing.fairplay.chat.controller;

import com.fairing.fairplay.chat.service.ChatPresenceService;
import com.fairing.fairplay.chat.service.UserPresenceService;
import com.fairing.fairplay.core.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat/presence")
@RequiredArgsConstructor
public class ChatPresenceController {

    private final ChatPresenceService chatPresenceService;
    private final UserPresenceService userPresenceService;

    // 기존 메서드들 (호환성 유지)
    @GetMapping
    public boolean isOnline(@RequestParam boolean isManager, @RequestParam Long userId) {
        return chatPresenceService.isOnline(isManager, userId);
    }
    
    @PostMapping("/online")
    public void setOnline(@RequestBody Map<String, Object> request) {
        Long userId = Long.valueOf(request.get("userId").toString());
        boolean isManager = Boolean.parseBoolean(request.get("isManager").toString());
        chatPresenceService.setOnline(isManager, userId);
        System.out.println("채팅방 열기 - 사용자 " + userId + " 온라인 상태로 설정");
    }
    
    @PostMapping("/offline")
    public void setOffline(@RequestBody Map<String, Object> request) {
        Long userId = Long.valueOf(request.get("userId").toString());
        boolean isManager = Boolean.parseBoolean(request.get("isManager").toString());
        chatPresenceService.setOffline(isManager, userId);
        System.out.println("브라우저 닫기 - 사용자 " + userId + " 오프라인 상태로 설정");
    }

    // 새로운 JWT 기반 메서드들
    @PostMapping("/connect")
    public void userConnect(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUserId();
        System.out.println("🟢 사용자 접속 요청: " + userId);
        chatPresenceService.setUserOnline(userId);
        System.out.println("✅ 사용자 온라인 상태 설정 완료: " + userId);
    }

    @PostMapping("/disconnect")
    public void userDisconnect(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUserId();
        System.out.println("🔴 사용자 연결해제 요청: " + userId);
        chatPresenceService.setUserOffline(userId);
        System.out.println("✅ 사용자 오프라인 상태 설정 완료: " + userId);
    }

    @GetMapping("/status/{userId}")
    public Map<String, Object> getUserStatus(@PathVariable Long userId) {
        // Redis 기반 온라인 상태 확인
        boolean isOnline = userPresenceService.isUserOnline(userId);
        return Map.of(
            "userId", userId,
            "isOnline", isOnline
        );
    }

    @GetMapping("/online-users")
    public Set<Long> getOnlineUsers() {
        // Redis에서 온라인 사용자 목록을 가져와서 Long으로 변환
        return userPresenceService.getOnlineUserIds().stream()
                .map(userIdStr -> {
                    try {
                        return Long.parseLong(userIdStr);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .filter(userId -> userId != null)
                .collect(Collectors.toSet());
    }

    // ADMIN 권한 사용자들의 온라인 상태 확인
    @GetMapping("/admin-status")
    public Map<String, Object> getAdminStatus() {
        Set<Long> onlineAdmins = chatPresenceService.getOnlineAdmins();
        boolean hasOnlineAdmin = !onlineAdmins.isEmpty();
        return Map.of(
            "hasOnlineAdmin", hasOnlineAdmin,
            "onlineAdmins", onlineAdmins,
            "adminCount", onlineAdmins.size()
        );
    }
}
