package com.faculte.simplefaculteauthenticate.domain.model.service.impl;

import com.faculte.simplefaculteauthenticate.domain.bean.AuthorityUser;
import com.faculte.simplefaculteauthenticate.domain.bean.User;
import com.faculte.simplefaculteauthenticate.domain.model.dao.UserDao;
import com.faculte.simplefaculteauthenticate.domain.model.service.AuthorityUserService;
import com.faculte.simplefaculteauthenticate.domain.model.service.UserService;
import com.faculte.simplefaculteauthenticate.domain.security.UserDetailsService;
import com.faculte.simplefaculteauthenticate.util.ListUtil;
import com.faculte.simplefaculteauthenticate.util.SearchUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private AuthorityUserService authorityuserService;
    @Autowired
    private UserDetailsService detailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        //    return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        if (user == null) {
            return null;
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);
            return user;
        }
    }

    @Override
    public User saveWithAuthorityUsers(User user) {

        if (user == null) {
            return null;
        } else {
            if (ListUtil.isEmpty(user.getAuthorityUsers())) {
                return null;
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                for (AuthorityUser authorityuser : user.getAuthorityUsers()) {
                    authorityuser.setUser(user);
                    authorityuserService.save(authorityuser);
                }
            }
            userDao.save(user);
            return user;
        }
    }

    private boolean valide(CharSequence cs, String string) {
        return passwordEncoder.matches(cs, string);
    }

    @Override
    public void authenticate(String username, String password) {
        UserDetails userDetails = (UserDetails) this.detailsService.loadUserByUsername(username);
        if (valide(password, userDetails.getPassword())) {
            log.info(userDetails);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    password, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            throw new BadCredentialsException(String.valueOf("coudln't authenticate using " + username + " ! "));
        }
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public User getCurrentUser() {
        return findByEmail(userDetailsService.getCurrentUsername());
    }

    @Override
    public User findByEmail(String username) {
        return userDao.findByEmail(username);
    }

    @Override
    public List< User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDao.getOne(id);
    }

    @Override
    public int delete(User user) {
        if (user == null) {
            return -1;
        } else {
            userDao.delete(user);
            return 1;
        }
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    public void clone(User user, User userClone) {
        if (user != null && userClone != null) {
            userClone.setId(user.getId());
            userClone.setEmail(user.getEmail());
            userClone.setPassword(user.getPassword());
            userClone.setAuthorityUsers(authorityuserService.clone(user.getAuthorityUsers()));
        }
    }

    public User clone(User user) {
        if (user == null) {
            return null;
        } else {
            User userClone = new User();
            clone(user, userClone);
            return userClone;
        }
    }

    public List<User> clone(List<User> users) {
        if (users == null) {
            return null;
        } else {
            List<User> usersClone = new ArrayList();
            users.forEach((user) -> {
                usersClone.add(clone(user));
            });
            return usersClone;
        }
    }

    @Override
    public List< User> findByCriteria(String email, String password, Long id) {
        return entityManager.createQuery(constructQuery(email, password, id)).getResultList();
    }

    private String constructQuery(String email, String password, Long id) {
        String query = "SELECT u FROM User u where 1=1 ";
        query += SearchUtil.addConstraint("u", "email", "=", email);
        query += SearchUtil.addConstraint("u", "password", "=", password);

        return query;
    }
}
