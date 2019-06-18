package com.faculte.simplefaculteauthenticate.domain.model.service.impl;

import com.faculte.simplefaculteauthenticate.domain.bean.AuthorityUser;
import com.faculte.simplefaculteauthenticate.domain.model.dao.AuthorityUserDao;
import com.faculte.simplefaculteauthenticate.domain.model.service.AuthorityService;
import com.faculte.simplefaculteauthenticate.domain.model.service.AuthorityUserService;
import com.faculte.simplefaculteauthenticate.domain.model.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityUserServiceImpl implements AuthorityUserService {

    @Autowired

    private AuthorityUserDao authorityuserDao;

    @Autowired

    private EntityManager entityManager;

    @Autowired

    private UserService userService;

    @Autowired

    private AuthorityService authorityService;

    @Override
    public AuthorityUser save(AuthorityUser authorityuser) {
        if (authorityuser == null) {
            return null;
        } else {
            authorityuserDao.save(authorityuser);
            return authorityuser;
        }
    }

    @Override
    public List< AuthorityUser> findAll() {
        return authorityuserDao.findAll();
    }

    @Override
    public AuthorityUser findById(Long id) {
        return authorityuserDao.getOne(id);
    }

    @Override
    public int delete(AuthorityUser authorityuser) {
        if (authorityuser == null) {
            return -1;
        } else {
            authorityuserDao.delete(authorityuser);
            return 1;
        }
    }

    @Override
    public void deleteById(Long id) {
        authorityuserDao.deleteById(id);
    }

    public void clone(AuthorityUser authorityuser, AuthorityUser authorityuserClone) {
        if (authorityuser != null && authorityuserClone != null) {
            authorityuserClone.setId(authorityuser.getId());
            authorityuserClone.setUser(userService.clone(authorityuser.getUser()));
            authorityuserClone.setAuthority(authorityService.clone(authorityuser.getAuthority()));
        }
    }

    public AuthorityUser clone(AuthorityUser authorityuser) {
        if (authorityuser == null) {
            return null;
        } else {
            AuthorityUser authorityuserClone = new AuthorityUser();
            clone(authorityuser, authorityuserClone);
            return authorityuserClone;
        }
    }

    public List<AuthorityUser> clone(List<AuthorityUser> authorityusers) {
        if (authorityusers == null) {
            return null;
        } else {
            List<AuthorityUser> authorityusersClone = new ArrayList();
            authorityusers.forEach((authorityuser) -> {
                authorityusersClone.add(clone(authorityuser));
            });
            return authorityusersClone;
        }
    }

    @Override
    public List< AuthorityUser> findByCriteria(Long id) {
        return entityManager.createQuery(constructQuery(id)).getResultList();
    }

    private String constructQuery(Long id) {
        String query = "SELECT a FROM AuthorityUser a where 1=1 ";

        return query;
    }
}
