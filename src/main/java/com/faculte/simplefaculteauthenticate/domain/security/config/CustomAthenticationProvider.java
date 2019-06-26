/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefaculteauthenticate.domain.security.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anas
 */
@Log4j2
@Component
public class CustomAthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService detailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private boolean valide(CharSequence cs, String string) {
        return passwordEncoder.matches(cs, string);
    }

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        UserDetails userDetails = (UserDetails) this.detailsService.loadUserByUsername(a.getName());
        if (valide(a.getCredentials().toString(), userDetails.getPassword())) {
            log.info(userDetails);
            return new UsernamePasswordAuthenticationToken(userDetails, a.getCredentials().toString(), userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException(String.valueOf("coudln't authenticate using " + a.getName() + " ! "));
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
