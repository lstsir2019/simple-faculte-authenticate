package com.faculte.simplefaculteauthenticate.domain.model.service;

import com.faculte.simplefaculteauthenticate.domain.bean.User;
import java.util.List;

public interface UserService {

    public User save(User user);

    public User saveWithAuthorityUsers(User user);

    public User getCurrentUser();

    public void authenticate(String username, String password);

    public User findByEmail(String username);

    public List<User> findAll();

    public User findById(Long id);

    public int delete(User user);

    public void deleteById(Long id);

    public void clone(User user, User userClone);

    public User clone(User user);

    public List<User> clone(List<User> users);

    public List<User> findByCriteria(String email, String password, Long id);

}
