package com.faculte.simplefaculteauthenticate.ws.rest.vo;

import com.faculte.simplefaculteauthenticate.domain.security.UserDetails;
import java.util.ArrayList;
import java.util.List;

public class UserVo {

    private String id;
    private String email;
    private String password;
    private String token;
    List<String> authorities=new ArrayList<>();
    private List<AuthorityUserVo> authorityUsersVo;

    public UserVo() {
    }

    public UserVo(UserDetails userDetails, String token) {
        this.email = userDetails.getUsername();
        this.password = userDetails.getPassword();
        //this.authorityUsersVo =
        userDetails.getAuthorities().forEach((authority) -> {
            this.authorities.add(authority.getAuthority());
        });
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

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }


    public void setAuthorityUserVo(List<AuthorityUserVo> AuthorityUsersVo) {
        this.authorityUsersVo = authorityUsersVo;
    }

}
