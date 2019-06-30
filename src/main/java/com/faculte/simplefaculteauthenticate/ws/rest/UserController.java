/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefaculteauthenticate.ws.rest;

import com.faculte.simplefaculteauthenticate.domain.bean.Authority;
import com.faculte.simplefaculteauthenticate.domain.bean.User;
import com.faculte.simplefaculteauthenticate.domain.model.service.AuthorityUserService;
import com.faculte.simplefaculteauthenticate.domain.model.service.UserService;
import com.faculte.simplefaculteauthenticate.domain.security.UserDetails;
import com.faculte.simplefaculteauthenticate.domain.security.UserDetailsService;
import com.faculte.simplefaculteauthenticate.ws.rest.converter.UserConverter;
import com.faculte.simplefaculteauthenticate.ws.rest.vo.UserVo;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gouss
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private AuthorityUserService authorityUserService;
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping(value = "/users")
    //  @RolesAllowed("Role_ADMIN")
    // @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserVo>> getAllUsers() {
        List<User> users = userService.findAll();
        userConverter.init();
        List<UserVo> usersVo = userConverter.toVo(users);
        return new ResponseEntity<>(usersVo, HttpStatus.OK);

    }

    @GetMapping(value = "/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserVo> getUser(Principal principal) {
        this.userConverter.init();
        UserVo user = userConverter.toVo(userService.findByEmail(principal.getName()));
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping("/account")
    public UserVo currentUserAuthorities() {
        this.userConverter.init();
        return userConverter.toVo(userService.getCurrentUser());
    }

    @GetMapping("/accountt")
    public Principal currentUserAuthorities(Principal principal) {
        return principal;
    }
}
