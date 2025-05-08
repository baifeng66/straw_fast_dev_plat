package com.daocao.common.domain.vo;

import cn.hutool.core.util.ObjectUtil;
import com.daocao.common.domain.po.UmsSysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: daocao
 * @Description: TODO
 * @Author: bf
 * @Date: 2025/3/23 23:35:39
 * @Version: 1.0
 */
@Data
public class LoginUserVo implements UserDetails {

    private Long id;
    private String token;
    // 用户信息
    private UmsSysUser umsSysUser = new UmsSysUser();
    // 登录时间
    private Long loginTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> perms = umsSysUser.getPerms();
        //
        if (ObjectUtil.isNotNull(perms)) {
            return perms.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public String getPassword() {
        return umsSysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return umsSysUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return umsSysUser.getStatus() == 0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return umsSysUser.getStatus() == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return umsSysUser.getStatus() == 0;
    }

    @Override
    public boolean isEnabled() {
        return umsSysUser.getStatus() == 0;
    }
}
