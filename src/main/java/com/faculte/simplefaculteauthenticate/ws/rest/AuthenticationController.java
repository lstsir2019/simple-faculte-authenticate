/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefaculteauthenticate.ws.rest;

import antlr.Token;
import com.faculte.simplefaculteauthenticate.config.CustomAthenticationProvider;
import com.faculte.simplefaculteauthenticate.domain.bean.User;
import com.faculte.simplefaculteauthenticate.domain.security.UserDetails;
import com.faculte.simplefaculteauthenticate.domain.security.jwt.TokenProvider;
import com.faculte.simplefaculteauthenticate.ws.rest.vo.UserVo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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

    @PostMapping(value = "/login")
    public ResponseEntity<UserVo> login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {

        try {
            Authentication authentication = athenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = tokenProvider.createToken(authentication, false);
            response.setHeader("Token", token);
            return new ResponseEntity<>(new UserVo(userDetails, token), HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(String.valueOf("coudln't authenticate using " + user.getEmail() + " ! "));
        }
    }
}
