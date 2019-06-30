package com.faculte.simplefaculteauthenticate.domain.model.service;

import com.faculte.simplefaculteauthenticate.domain.bean.Authority;
import com.faculte.simplefaculteauthenticate.domain.bean.AuthorityUser;
import java.util.List;

public interface AuthorityUserService {

    public AuthorityUser save(AuthorityUser authorityuser);

    public List<AuthorityUser> findAll();

    public AuthorityUser findById(Long id);

    public int delete(AuthorityUser authorityuser);

    public void deleteById(Long id);

    public void clone(AuthorityUser authorityuser, AuthorityUser authorityuserClone);

    List<Authority> findAuthorityByUserEmail(String email);

    public AuthorityUser clone(AuthorityUser authorityuser);

    public List<AuthorityUser> clone(List<AuthorityUser> authorityusers);

    public List<AuthorityUser> findByCriteria(Long id);

}
