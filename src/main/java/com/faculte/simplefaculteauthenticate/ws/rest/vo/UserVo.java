package com.faculte.simplefaculteauthenticate.ws.rest.vo;

import com.faculte.simplefaculteauthenticate.domain.security.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

public class UserVo {

    private String id;
    private String email;
    private String password;
    private String token;
    Collection<? extends GrantedAuthority> authorities;
    private List<AuthorityUserVo> authorityUsersVo;

    public UserVo() {
    }

    public UserVo(UserDetails userDetails, String token) {
        this.email = userDetails.getUsername();
        this.password = userDetails.getPassword();
        //this.authorityUsersVo =
        this.authorities = userDetails.getAuthorities();
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    public List<AuthorityUserVo> getAuthorityUsersVo() {
        return authorityUsersVo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setAuthorityUserVo(List<AuthorityUserVo> AuthorityUsersVo) {
        this.authorityUsersVo = authorityUsersVo;
    }

}
