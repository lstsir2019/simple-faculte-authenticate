package com.faculte.simplefaculteauthenticate.ws.rest.converter;

import com.faculte.simplefaculteauthenticate.domain.bean.AuthorityUser;
import com.faculte.simplefaculteauthenticate.util.NumberUtil;
import com.faculte.simplefaculteauthenticate.ws.rest.vo.AuthorityUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorityUserConverter implements AbstractConverter<AuthorityUser, AuthorityUserVo> {

    private boolean user;

    @Autowired
    private UserConverter userConverter;
    private boolean authority;

    @Autowired
    private AuthorityConverter authorityConverter;

    @Override
    public AuthorityUser toItem(AuthorityUserVo vo) {
        if (vo == null) {
            return null;
        } else {
            AuthorityUser item = new AuthorityUser();

            if (user && vo.getUserVo() != null) {
                item.setUser(userConverter.toItem(vo.getUserVo()));
            }

            if (authority && vo.getAuthorityVo() != null) {
                item.setAuthority(authorityConverter.toItem(vo.getAuthorityVo()));
            }

            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }

            return item;
        }
    }

    @Override
    public AuthorityUserVo toVo(AuthorityUser item) {
        if (item == null) {
            return null;
        } else {
            AuthorityUserVo vo = new AuthorityUserVo();

            if (user && item.getUser() != null) {
                vo.setUserVo(userConverter.toVo(item.getUser()));
            }

            if (authority && item.getAuthority() != null) {
                vo.setAuthorityVo(authorityConverter.toVo(item.getAuthority()));
            }

            vo.setId(NumberUtil.toString(item.getId()));

            return vo;
        }
    }

    public void init() {

        user = true;

        authority = true;
    }
}
