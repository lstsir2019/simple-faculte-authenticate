package com.faculte.simplefaculteauthenticate.ws.rest.vo;

import com.faculte.simplefaculteauthenticate.domain.security.UserDetails;
import java.util.ArrayList;
import java.util.List;

public class UserVo {
    
    private String id;
    private String email;
    List<String> authorities = new ArrayList<>();
    private List<AuthorityVo> authorityVos = new ArrayList<>();
    
    public UserVo() {
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
    
    public List<String> getAuthorities() {
        return authorities;
    }
    
    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
    
    public List<AuthorityVo> getAuthorityVos() {
        return authorityVos;
    }
    
    public void setAuthorityVos(List<AuthorityVo> authorityVos) {
        this.authorityVos = authorityVos;
    }
    
}
