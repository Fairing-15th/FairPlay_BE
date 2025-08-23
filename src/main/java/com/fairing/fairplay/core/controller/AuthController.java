package com.fairing.fairplay.core.controller;

import com.fairing.fairplay.core.dto.KakaoLoginRequest;
import com.fairing.fairplay.core.dto.LoginRequest;
import com.fairing.fairplay.core.dto.LoginResponse;
import com.fairing.fairplay.core.dto.RefreshTokenRequest;
import com.fairing.fairplay.core.dto.SessionInfo;
import com.fairing.fairplay.core.security.CustomSessionUserDetails;
import com.fairing.fairplay.core.security.CustomUserDetails;
import com.fairing.fairplay.core.service.AuthService;
import com.fairing.fairplay.core.service.RefreshTokenService;
import com.fairing.fairplay.core.service.SessionService;
import com.fairing.fairplay.core.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final SessionService sessionService;
    private final CookieUtil cookieUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.badRequest().build();
        }
        
        refreshTokenService.deleteRefreshToken(userDetails.getUserId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody @Valid RefreshTokenRequest request) {
        LoginResponse response = authService.refreshToken(request.getRefreshToken());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/kakao")
    public ResponseEntity<LoginResponse> kakaoLogin(@RequestBody KakaoLoginRequest request) {
        LoginResponse response = authService.kakaoLogin(request.getCode());
        return ResponseEntity.ok(response);
    }

    // === 새로운 세션 기반 인증 엔드포인트 ===
    
    @PostMapping("/session/login")
    public ResponseEntity<String> loginWithSession(
            @RequestBody @Valid LoginRequest request,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse
    ) {
        String userAgent = httpRequest.getHeader("User-Agent");
        String ipAddress = getClientIpAddress(httpRequest);
        
        String sessionId = authService.loginWithSession(request, userAgent, ipAddress);
        cookieUtil.addSessionCookie(httpResponse, sessionId);
        
        return ResponseEntity.ok("로그인 성공");
    }
    
    @PostMapping("/session/kakao")
    public ResponseEntity<String> kakaoLoginWithSession(
            @RequestBody KakaoLoginRequest request,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse
    ) {
        String userAgent = httpRequest.getHeader("User-Agent");
        String ipAddress = getClientIpAddress(httpRequest);
        
        String sessionId = authService.kakaoLoginWithSession(request.getCode(), userAgent, ipAddress);
        cookieUtil.addSessionCookie(httpResponse, sessionId);
        
        return ResponseEntity.ok("카카오 로그인 성공");
    }
    
    @PostMapping("/session/logout")
    public ResponseEntity<String> logoutSession(
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse
    ) {
        String sessionId = cookieUtil.getSessionId(httpRequest);
        
        if (sessionId != null) {
            authService.logoutSession(sessionId);
        }
        
        cookieUtil.removeSessionCookie(httpResponse);
        return ResponseEntity.ok("로그아웃 성공");
    }
    
    @GetMapping("/session/info")
    public ResponseEntity<SessionInfo> getSessionInfo(
            @AuthenticationPrincipal CustomSessionUserDetails userDetails
    ) {
        if (userDetails == null) {
            return ResponseEntity.status(401).build();
        }
        
        return ResponseEntity.ok(userDetails.getSessionInfo());
    }
    
    @PostMapping("/session/extend")
    public ResponseEntity<String> extendSession(
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse
    ) {
        String sessionId = cookieUtil.getSessionId(httpRequest);
        
        if (sessionId != null && sessionService.isValidSession(sessionId)) {
            sessionService.extendSession(sessionId);
            cookieUtil.refreshSessionCookie(httpResponse, sessionId);
            return ResponseEntity.ok("세션 연장 성공");
        }
        
        return ResponseEntity.status(401).body("유효하지 않은 세션");
    }
    
    /**
     * 클라이언트 IP 주소 추출 (프록시 고려)
     */
    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty() && !"unknown".equalsIgnoreCase(xForwardedFor)) {
            return xForwardedFor.split(",")[0].trim();
        }
        
        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty() && !"unknown".equalsIgnoreCase(xRealIp)) {
            return xRealIp;
        }
        
        return request.getRemoteAddr();
    }

}
