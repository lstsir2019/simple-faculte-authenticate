package com.faculte.simplefaculteauthenticate.ws.rest.converter;

import com.faculte.simplefaculteauthenticate.domain.bean.AuthorityUser;
import com.faculte.simplefaculteauthenticate.domain.bean.User;
import com.faculte.simplefaculteauthenticate.util.ListUtil;
import com.faculte.simplefaculteauthenticate.util.NumberUtil;
import com.faculte.simplefaculteauthenticate.util.StringUtil;
import com.faculte.simplefaculteauthenticate.ws.rest.vo.UserVo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements AbstractConverter<User, UserVo> {

    private boolean authorityUsers = true;
    private boolean authorities = true;

    @Autowired
    private AuthorityUserConverter authorityUserConverter;
    @Autowired
    private AuthorityConverter authorityConverter;

    @Override
    public User toItem(UserVo vo) {
        if (vo == null) {
            return null;
        } else {
            User item = new User();

            if (StringUtil.isNotEmpty(vo.getEmail())) {
                item.setEmail(vo.getEmail());
            }

            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }

            if (ListUtil.isNotEmpty(vo.getAuthorities()) && authorityUsers) {
                this.authorityUsers = false;
                item.setAuthorityUsers(authorityUserConverter.toItem(item, vo.getAuthorities()));
            }
            if (ListUtil.isNotEmpty(vo.getAuthorities()) && authorities) {
                this.authorities = false;
                item.setAuthorityUsers(authorityUserConverter.toItem(item, vo.getAuthorities()));
            }

            return item;
        }
    }

    @Override
    public UserVo toVo(User item) {
        if (item == null) {
            return null;
        } else {
            UserVo vo = new UserVo();
            vo.setEmail(item.getEmail());
            vo.setId(NumberUtil.toString(item.getId()));
            if (ListUtil.isNotEmpty(item.getAuthorityUsers())) {
                item.getAuthorityUsers().forEach((AuthorityUser role) -> {
                    vo.getAuthorityVos().add(authorityConverter.toVo(role.getAuthority()));
                });
            }
            return vo;
        }
    }

    public void init() {
        this.authorityUsers = true;
        this.authorities = true;
    }

    public boolean isAuthorityUsers() {
        return authorityUsers;
    }

    public void setAuthorityUsers(boolean authorityUsers) {
        this.authorityUsers = authorityUsers;
    }

    public AuthorityUserConverter getAuthorityUserConverter() {
        return authorityUserConverter;
    }

    public void setAuthorityUserConverter(AuthorityUserConverter authorityUserConverter) {
        this.authorityUserConverter = authorityUserConverter;
    }

}
