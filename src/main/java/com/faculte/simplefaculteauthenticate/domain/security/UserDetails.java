package com.faculte.simplefaculteauthenticate.domain.security;

import com.faculte.simplefaculteauthenticate.domain.bean.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private final  User user;

    private final Set<GrantedAuthority> authorities;

    public UserDetails(User user) {
        this.user = user;
        this.authorities = this.user.getAuthorityUsers()
                .stream()
                .map(au -> new SimpleGrantedAuthority("ROLE_" + au.getAuthority().getAuthority().toUpperCase()))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
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
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "UserDetails{" + "user=" + user + ", authorities=" + authorities + '}';
    }
}
