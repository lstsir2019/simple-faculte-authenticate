/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefaculteauthenticate.ws.rest;

import com.faculte.simplefaculteauthenticate.domain.security.config.CustomAthenticationProvider;
import com.faculte.simplefaculteauthenticate.domain.security.jwt.JWTConfigurer;
import com.faculte.simplefaculteauthenticate.domain.security.jwt.JWTToken;
import com.faculte.simplefaculteauthenticate.domain.security.jwt.TokenProvider;
import com.faculte.simplefaculteauthenticate.ws.rest.vo.LoginVo;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gouss
 */
@RestController
public class AuthenticationController {

    @Autowired
    private CustomAthenticationProvider athenticationProvider;
    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVo loginVo) {

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginVo.getEmail(), loginVo.getPassword());
        Authentication authentication = athenticationProvider.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (null == loginVo.isRememberMe()) ? false : loginVo.isRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }
}
