package com.faculte.simplefaculteauthenticate.ws.rest.converter;

import com.faculte.simplefaculteauthenticate.domain.bean.User;
import com.faculte.simplefaculteauthenticate.util.ListUtil;
import com.faculte.simplefaculteauthenticate.util.NumberUtil;
import com.faculte.simplefaculteauthenticate.util.StringUtil;
import com.faculte.simplefaculteauthenticate.ws.rest.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements AbstractConverter<User, UserVo> {

    private boolean authorityUsers = true;

    @Autowired
    private AuthorityUserConverter authorityUserConverter;

    @Override
    public User toItem(UserVo vo) {
        if (vo == null) {
            return null;
        } else {
            User item = new User();

            if (StringUtil.isNotEmpty(vo.getEmail())) {
                item.setEmail(vo.getEmail());
            }

            if (StringUtil.isNotEmpty(vo.getPassword())) {
                item.setPassword(vo.getPassword());
            }

            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }

            if (ListUtil.isNotEmpty(vo.getAuthorityUsersVo()) && authorityUsers) {
                item.setAuthorityUsers(authorityUserConverter.toItem(vo.getAuthorityUsersVo()));
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
            vo.setPassword(item.getPassword());
            vo.setId(NumberUtil.toString(item.getId()));
            if (ListUtil.isNotEmpty(item.getAuthorityUsers()) && authorityUsers) {
                vo.setAuthorityUserVo(authorityUserConverter.toVo(item.getAuthorityUsers()));
            }

            return vo;
        }
    }

    public void init() {
        authorityUsers = true;
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
