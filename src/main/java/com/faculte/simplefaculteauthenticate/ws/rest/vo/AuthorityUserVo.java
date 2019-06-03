package com.faculte.simplefaculteauthenticate.ws.rest.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

public class AuthorityUserVo {

    private String id;
    private UserVo userVo;
    private AuthorityVo authorityVo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    @JsonIgnore
    public AuthorityVo getAuthorityVo() {
        return authorityVo;
    }

    public void setAuthorityVo(AuthorityVo authorityVo) {
        this.authorityVo = authorityVo;
    }

}
