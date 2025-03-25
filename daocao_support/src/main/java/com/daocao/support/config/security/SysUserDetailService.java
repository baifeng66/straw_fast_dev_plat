package com.daocao.support.config.security;


import com.daocao.common.domain.po.UmsMenu;
import com.daocao.common.domain.po.UmsRole;
import com.daocao.common.domain.po.UmsSysUser;
import com.daocao.common.domain.vo.LoginUserVo;
import com.daocao.common.mapper.UmsMenuMapper;
import com.daocao.common.mapper.UmsSysUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: daocao
 * @Description: 用户信息获取
 * @Author: bf
 * @Date: 2025/3/23 23:38:46
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserDetailService implements UserDetailsService {

    private final UmsSysUserMapper umsSysUserMapper;
    private final UmsMenuMapper umsMenuMapper;

    private final static String PHONE_REGEX = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))\\d{8}$";
    private final static String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    /**
     * @Description: 根据用户名获取用户信息
     * 根据邮件、手机号、账号...登录，可以写个正则表达式判断
     * @param account
     * @return UserDetails
     * @throws
     * @Author: bf
     * @Date: 2025/3/23 23:39
     */
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        // 使用正则表达式登录类型
        Integer accountType = null;
        if (account.matches(EMAIL_REGEX)) {
            //邮箱登录
            accountType = 1;
        } else if (account.matches(PHONE_REGEX)) {
            //手机号登录
            accountType = 2;
        } else {
            //账号登录
            accountType = 3;
        }
        // 根据账号将用户以及其角色信息查询出来
        UmsSysUser umsSysUser = umsSysUserMapper.selectByAccount(accountType, account);
        log.info("umsSysUser====>{}", umsSysUser);
        if (umsSysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 权限查询是根据角色查询的
        List<Long> roleIds = umsSysUser.getRoles().stream().map(UmsRole::getRoleId).toList();

        // 根据角色查询权限
        List<UmsMenu> umsMenus = umsMenuMapper.selectByRoleIds(roleIds);
        // 获取权限
        List<String> perms = umsMenus.stream().map(UmsMenu::getPerms).toList();
        log.info("perms====>{}", perms);
        umsSysUser.setPerms(perms);

        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setUmsSysUser(umsSysUser);
        loginUserVo.setId(umsSysUser.getId());
        return loginUserVo;
    }
}
