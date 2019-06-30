/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefaculteauthenticate.ws.rest.vo;

import java.util.List;

/**
 *
 * @author A O
 */
public class ManagedUserVo extends UserVo {

    private String password;

    public ManagedUserVo() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
