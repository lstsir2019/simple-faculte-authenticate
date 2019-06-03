package com.faculte.simplefaculteauthenticate.domain.model.dao;

import com.faculte.simplefaculteauthenticate.domain.bean.AuthorityUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityUserDao extends JpaRepository<AuthorityUser,Long> {
}
