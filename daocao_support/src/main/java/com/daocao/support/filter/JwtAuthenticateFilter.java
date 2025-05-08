package com.daocao.support.filter;

import cn.hutool.core.util.ObjectUtil;
import com.daocao.common.domain.vo.LoginUserVo;
import com.daocao.common.utils.JWTUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Project: daocao
 * @Description: Jwt认证过滤器
 * @Author: bf
 * @Date: 2025/3/25 12:26:46
 * @Version: 1.0
 */
@Component
@Slf4j
public class JwtAuthenticateFilter extends OncePerRequestFilter {

    @Resource
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 获取登录用户信息
        LoginUserVo loginUserVo = jwtUtil.getLoginUser(request);
        if (ObjectUtil.isNotEmpty(loginUserVo)) {
            // 鉴权
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUserVo, null, loginUserVo.getAuthorities());
            // 登录成功，设置用户信息到 SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        // 放行
        filterChain.doFilter(request, response);
    }
}
