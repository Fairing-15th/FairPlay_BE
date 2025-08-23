package com.fairing.fairplay.core.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CookieUtil {

    @Value("${session.cookie-name:SESSION_ID}")
    private String sessionCookieName;

    @Value("${session.cookie-max-age:1800}") // 30분 기본값
    private int sessionCookieMaxAge;

    @Value("${session.cookie-secure:true}")
    private boolean sessionCookieSecure;

    @Value("${session.cookie-domain:}")
    private String sessionCookieDomain;

    /**
     * 세션 쿠키 생성 및 응답에 추가
     */
    public void addSessionCookie(HttpServletResponse response, String sessionId) {
        Cookie sessionCookie = new Cookie(sessionCookieName, sessionId);
        sessionCookie.setHttpOnly(true); // XSS 방지
        sessionCookie.setSecure(sessionCookieSecure); // HTTPS 환경에서만 전송
        sessionCookie.setSameSite(Cookie.SameSite.LAX); // CSRF 방지
        sessionCookie.setPath("/"); // 모든 경로에서 접근 가능
        sessionCookie.setMaxAge(sessionCookieMaxAge); // 쿠키 만료 시간 (초 단위)
        
        if (sessionCookieDomain != null && !sessionCookieDomain.trim().isEmpty()) {
            sessionCookie.setDomain(sessionCookieDomain);
        }
        
        response.addCookie(sessionCookie);
        log.debug("Session cookie added: {} (maxAge: {})", sessionId, sessionCookieMaxAge);
    }

    /**
     * 세션 쿠키 제거 (로그아웃 등)
     */
    public void removeSessionCookie(HttpServletResponse response) {
        Cookie sessionCookie = new Cookie(sessionCookieName, null);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setSecure(sessionCookieSecure);
        sessionCookie.setSameSite(Cookie.SameSite.LAX);
        sessionCookie.setPath("/");
        sessionCookie.setMaxAge(0); // 즉시 만료
        
        if (sessionCookieDomain != null && !sessionCookieDomain.trim().isEmpty()) {
            sessionCookie.setDomain(sessionCookieDomain);
        }
        
        response.addCookie(sessionCookie);
        log.debug("Session cookie removed");
    }

    /**
     * 요청에서 세션 ID 추출
     */
    public String getSessionId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (sessionCookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 세션 쿠키 업데이트 (TTL 갱신)
     */
    public void refreshSessionCookie(HttpServletResponse response, String sessionId) {
        addSessionCookie(response, sessionId); // 새로운 만료시간으로 쿠키 재설정
    }
}