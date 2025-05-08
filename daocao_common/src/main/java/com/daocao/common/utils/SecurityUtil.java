package com.daocao.common.utils;

import com.daocao.common.constants.HttpStatus;
import com.daocao.common.domain.vo.LoginUserVo;
import com.daocao.common.exception.ServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Project: daocao
 * @Description: TODO
 * @Author: bf
 * @Date: 2025/3/25 16:40:03
 * @Version: 1.0
 */
public class SecurityUtil {
    // 获取当前经过身份验证的主体或身份验证请求令牌
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    // 获取当前登录用户信息
    public static LoginUserVo getLoginUserVo() {
        return (LoginUserVo) getAuthentication().getPrincipal();
    }

    // 获取登录用户id
    public static Long getLoginUserId() {
        Long id = getLoginUserVo().getId();
        if (id == null) {
            throw new ServiceException(HttpStatus.FORBIDDEN, "");
        }
        return id;
    }

    // 获取登录用户名
    public static String getLoginUserName() {
        String username = getLoginUserVo().getUsername();
        if (username == null) {
            throw new ServiceException(HttpStatus.FORBIDDEN, "");
        }
        return username;
    }


}
