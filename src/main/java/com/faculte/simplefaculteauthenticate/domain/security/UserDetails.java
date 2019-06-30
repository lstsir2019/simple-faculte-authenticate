package com.faculte.simplefaculteauthenticate.domain.security;

import com.faculte.simplefaculteauthenticate.domain.bean.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private String password;
    private String username;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Set<GrantedAuthority> authorities;

    public UserDetails(com.faculte.simplefaculteauthenticate.domain.security.UserDetails userDetails) {
        this.username = userDetails.getUsername();
        this.password = userDetails.getPassword();
        this.accountNonExpired = userDetails.isAccountNonExpired();
        this.accountNonLocked = userDetails.isAccountNonLocked();
        this.credentialsNonExpired = userDetails.isCredentialsNonExpired();
        this.enabled = userDetails.isEnabled();
        this.authorities = userDetails.getAuthorities();
    }

    public UserDetails(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.authorities = user.getAuthorityUsers()
                .stream()
                .map(au -> new SimpleGrantedAuthority("ROLE_" + au.getAuthority().getAuthority().toUpperCase()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
