package com.daocao.common.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.daocao.common.constants.HttpStatus;
import com.daocao.common.domain.dto.LoginDto;
import com.daocao.common.domain.vo.LoginUserVo;
import com.daocao.common.exception.ServiceException;
import com.daocao.common.service.IAuthService;
import com.daocao.common.utils.JWTUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @Project: daocao
 * @Description: TODO
 * @Author: bf
 * @Date: 2025/3/23 23:31:28
 * @Version: 1.0
 */
@Service
@Slf4j
public class AuthServiceImpl implements IAuthService {

    @Resource
    private JWTUtil jwtUtil;
    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginDto dto) {
        // 验证用户名密码
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(dto.getAccount(), dto.getPassword());
        // 尝试对传递的身份验证对象进行身份验证，如果成功，则返回一个完全填充的身份验证对象
        Authentication authenticate = authenticationManager.authenticate(authentication);
        // 获取用户信息(返回的是 UserDetails)
        LoginUserVo loginUserVo = (LoginUserVo) authenticate.getPrincipal();
        if (ObjectUtil.isNull(loginUserVo)) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, "认证失败");
        }
        // 生成token
        return jwtUtil.createToken(loginUserVo);
    }
}
