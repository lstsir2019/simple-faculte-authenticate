package com.faculte.simplefaculteauthenticate.domain.model.service.impl;

import com.faculte.simplefaculteauthenticate.domain.bean.Authority;
import com.faculte.simplefaculteauthenticate.domain.model.dao.AuthorityDao;
import com.faculte.simplefaculteauthenticate.domain.model.service.AuthorityService;
import com.faculte.simplefaculteauthenticate.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class AuthorityServiceImpl implements AuthorityService {

    @Autowired

    private AuthorityDao authorityDao;

    @Autowired

    private EntityManager entityManager;

    @Override
    public Authority save(Authority authority) {
        if (authority == null) {
            return null;
        } else {            
            authorityDao.save(authority);
            return authority;
        }
    }

    @Override
    public List< Authority> findAll() {
        return authorityDao.findAll();
    }

    @Override
    public Authority findById(Long id) {
        return authorityDao.getOne(id);
    }

    @Override
    public int delete(Authority authority) {
        if (authority == null) {
            return -1;
        } else {
            authorityDao.delete(authority);
            return 1;
        }
    }

    @Override
    public void deleteById(Long id) {
        authorityDao.deleteById(id);
    }

    @Override
    public void clone(Authority authority, Authority authorityClone) {
        if (authority != null && authorityClone != null) {
            authorityClone.setId(authority.getId());
            authorityClone.setAuthority(authority.getAuthority());
        }
    }

    @Override
    public Authority clone(Authority authority) {
        if (authority == null) {
            return null;
        } else {
            Authority authorityClone = new Authority();
            clone(authority, authorityClone);
            return authorityClone;
        }
    }

    @Override
    public List<Authority> clone(List<Authority> authoritys) {
        if (authoritys == null) {
            return null;
        } else {
            List<Authority> authoritysClone = new ArrayList();
            authoritys.forEach((authority) -> {
                authoritysClone.add(clone(authority));
            });
            return authoritysClone;
        }
    }

    @Override
    public List< Authority> findByCriteria(String authority, Long id) {
        return entityManager.createQuery(constructQuery(authority, id)).getResultList();
    }

    private String constructQuery(String authority, Long id) {
        String query = "SELECT a FROM Authority a where 1=1 ";
        query += SearchUtil.addConstraint("a", "authority", "=", authority);

        return query;
    }
}
