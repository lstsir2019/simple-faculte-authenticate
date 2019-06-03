package com.faculte.simplefaculteauthenticate.domain.model.service;

import com.faculte.simplefaculteauthenticate.domain.bean.Authority;
import java.util.List;

public interface AuthorityService {

    public Authority save(Authority authority);

    public List<Authority> findAll();

    public Authority findById(Long id);

    public int delete(Authority authority);

    public void deleteById(Long id);

    public void clone(Authority authority, Authority authorityClone);

    public Authority clone(Authority authority);

    public List<Authority> clone(List<Authority> authoritys);

    public List<Authority> findByCriteria(String authority, Long id);

}
