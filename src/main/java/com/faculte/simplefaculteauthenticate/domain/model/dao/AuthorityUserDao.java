package com.faculte.simplefaculteauthenticate.domain.model.dao;

import com.faculte.simplefaculteauthenticate.domain.bean.Authority;
import com.faculte.simplefaculteauthenticate.domain.bean.AuthorityUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityUserDao extends JpaRepository<AuthorityUser, Long> {

    List<Authority> findAuthorityByUserEmail(String email);
}
