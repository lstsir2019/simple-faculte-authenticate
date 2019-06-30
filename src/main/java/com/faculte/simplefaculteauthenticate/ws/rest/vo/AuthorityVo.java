package com.faculte.simplefaculteauthenticate.ws.rest.vo;

import java.util.ArrayList;
import java.util.List;

public class AuthorityVo {

    private String id;
    private String authority;

    public AuthorityVo(String authority) {
        this.authority = authority;
    }

    public AuthorityVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
