package com.faculte.simplefaculteauthenticate.ws.rest.vo;

import com.faculte.simplefaculteauthenticate.domain.bean.Authority;
import com.faculte.simplefaculteauthenticate.domain.security.UserDetails;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserVo {

    private String id;
    private String email;
    List<AuthorityVo> authorities = new ArrayList<>();

    public UserVo() {
        // needed
    }

    public UserVo(UserDetails userDetails) {
        if (userDetails != null) {
            this.email = userDetails.getUsername();
            this.authorities = userDetails
                    .getAuthorities()
                    .stream()
                    .map(au -> new AuthorityVo(au.getAuthority()))
                    .collect(Collectors.toList());
        }
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

    public List<AuthorityVo> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityVo> authorities) {
        this.authorities = authorities;
    }

}
