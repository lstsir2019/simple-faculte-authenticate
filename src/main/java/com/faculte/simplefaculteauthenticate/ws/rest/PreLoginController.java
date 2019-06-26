/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefaculteauthenticate.ws.rest;

import com.faculte.simplefaculteauthenticate.domain.bean.User;
import com.faculte.simplefaculteauthenticate.domain.model.service.UserService;
import com.faculte.simplefaculteauthenticate.ws.rest.converter.UserConverter;
import com.faculte.simplefaculteauthenticate.ws.rest.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gouss
 */
@RestController
public class PreLoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;
    

    @PostMapping(value = "/registration")
    public ResponseEntity<String> registration(@RequestBody UserVo userVo) {
        userConverter.init();
        userConverter.setAuthorityUsers(true);
        User dbUser = userService.save(userConverter.toItem(userVo));
        if (dbUser != null) {
            return new ResponseEntity<>(("user created successfully"), HttpStatus.OK);
        }
        return null;
    }

}
