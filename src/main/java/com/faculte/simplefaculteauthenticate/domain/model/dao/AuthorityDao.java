package com.faculte.simplefaculteauthenticate.domain.model.dao;

import com.faculte.simplefaculteauthenticate.domain.bean.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityDao extends JpaRepository<Authority,Long> {
}
