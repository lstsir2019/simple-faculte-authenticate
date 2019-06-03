package com.faculte.simplefaculteauthenticate.ws.rest.converter;

import com.faculte.simplefaculteauthenticate.domain.bean.Authority;
import com.faculte.simplefaculteauthenticate.util.NumberUtil;
import com.faculte.simplefaculteauthenticate.util.StringUtil;
import com.faculte.simplefaculteauthenticate.ws.rest.vo.AuthorityVo;
import org.springframework.stereotype.Component;

@Component
public class AuthorityConverter implements AbstractConverter<Authority, AuthorityVo> {

    @Override
    public Authority toItem(AuthorityVo vo) {
        if (vo == null) {
            return null;
        } else {
            Authority item = new Authority();
            if (StringUtil.isNotEmpty(vo.getAuthority())) {
                item.setAuthority(vo.getAuthority());
            }
            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }
            return item;
        }
    }

    @Override
    public AuthorityVo toVo(Authority item) {
        if (item == null) {
            return null;
        } else {
            AuthorityVo vo = new AuthorityVo();
            vo.setAuthority(item.getAuthority());
            vo.setId(NumberUtil.toString(item.getId()));
            return vo;
        }
    }

    public void init() {
    }
}
