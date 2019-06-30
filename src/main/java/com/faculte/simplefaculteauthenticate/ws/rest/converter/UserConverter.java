package com.faculte.simplefaculteauthenticate.ws.rest.converter;

import com.faculte.simplefaculteauthenticate.domain.bean.User;
import com.faculte.simplefaculteauthenticate.util.ListUtil;
import com.faculte.simplefaculteauthenticate.util.NumberUtil;
import com.faculte.simplefaculteauthenticate.util.StringUtil;
import com.faculte.simplefaculteauthenticate.ws.rest.vo.UserVo;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements AbstractConverter<User, UserVo> {

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
            if (ListUtil.isNotEmpty(item.getAuthorityUsers()) && authorities) {
                this.authorities = false;
                vo.setAuthorities(item.getAuthorityUsers()
                        .stream()
                        .map(au -> authorityConverter.toVo(au.getAuthority()))
                        .collect(Collectors.toList()));
            }
            return vo;
        }
    }

    public void init() {
        this.authorities = true;
    }

    public boolean isAuthorities() {
        return authorities;
    }

    public void setAuthorities(boolean authorities) {
        this.authorities = authorities;
    }

    public AuthorityUserConverter getAuthorityUserConverter() {
        return authorityUserConverter;
    }

    public void setAuthorityUserConverter(AuthorityUserConverter authorityUserConverter) {
        this.authorityUserConverter = authorityUserConverter;
    }

}
