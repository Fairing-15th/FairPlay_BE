package com.fairing.fairplay.core.security;

import com.fairing.fairplay.core.dto.SessionInfo;
import com.fairing.fairplay.core.service.SessionService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
public class SessionAuthenticationFilter extends OncePerRequestFilter {

    private final SessionService sessionService;

    @Value("${session.cookie-name:SESSION_ID}")
    private String sessionCookieName;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        
        String sessionId = resolveSessionId(request);

        if (sessionId != null) {
            SessionInfo sessionInfo = sessionService.getSession(sessionId);
            
            if (sessionInfo != null) {
                // 세션 정보를 바탕으로 Spring Security 인증 객체 생성
                CustomSessionUserDetails userDetails = new CustomSessionUserDetails(sessionInfo);
                
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + sessionInfo.getRoleName()))
                        );
                
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
                log.debug("Authentication set for user: {} with session: {}", sessionInfo.getUserId(), sessionId);
            } else {
                log.debug("Invalid or expired session: {}", sessionId);
                // 만료된 세션 쿠키 제거
                clearSessionCookie(response);
            }
        }
        
        filterChain.doFilter(request, response);
    }

    /**
     * HttpOnly 쿠키에서 세션 ID 추출
     */
    private String resolveSessionId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (sessionCookieName.equals(cookie.getName()) && StringUtils.hasText(cookie.getValue())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 만료된 세션 쿠키 제거
     */
    private void clearSessionCookie(HttpServletResponse response) {
        Cookie sessionCookie = new Cookie(sessionCookieName, null);
        sessionCookie.setMaxAge(0);
        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true);
        sessionCookie.setSecure(true); // HTTPS 환경에서만 전송
        sessionCookie.setSameSite(Cookie.SameSite.LAX);
        response.addCookie(sessionCookie);
    }
}