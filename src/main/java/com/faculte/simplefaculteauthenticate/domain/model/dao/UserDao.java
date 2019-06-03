package com.faculte.simplefaculteauthenticate.domain.model.dao;

import com.faculte.simplefaculteauthenticate.domain.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    public User findByEmail(String username);
}
