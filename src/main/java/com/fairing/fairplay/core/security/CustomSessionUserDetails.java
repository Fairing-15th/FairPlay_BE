package com.fairing.fairplay.core.security;

import com.fairing.fairplay.core.dto.SessionInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class CustomSessionUserDetails implements UserDetails {

    private final SessionInfo sessionInfo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + sessionInfo.getRoleName()));
    }

    @Override
    public String getPassword() {
        // 세션 기반 인증에서는 비밀번호 불필요
        return null;
    }

    @Override
    public String getUsername() {
        return sessionInfo.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // 세션 정보 접근을 위한 메서드들
    public Long getUserId() {
        return sessionInfo.getUserId();
    }

    public String getEmail() {
        return sessionInfo.getEmail();
    }

    public String getName() {
        return sessionInfo.getName();
    }

    public String getRoleName() {
        return sessionInfo.getRoleName();
    }

    public Integer getRoleId() {
        return sessionInfo.getRoleId();
    }

    public String getPhone() {
        return sessionInfo.getPhone();
    }

    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }
}