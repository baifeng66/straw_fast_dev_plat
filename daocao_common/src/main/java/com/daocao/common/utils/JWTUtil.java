package com.daocao.common.utils;

import cn.hutool.core.lang.UUID;
import com.daocao.common.constants.CacheConstants;
import com.daocao.common.domain.vo.LoginUserVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Project: daocao
 * @Description: JWT工具类, 通过jwt生成token和解析token，刷新token
 * @Author: bf
 * @Date: 2025/3/24 15:40:26
 * @Version: 1.0
 */
@Component
public class JWTUtil {

    @Getter
    @Setter
    @Value("${bf.jwt.secret}")
    private String secret;  // 密钥

    @Getter
    @Setter
    @Value("${bf.token-header}")
    private String tokenHeader;

    @Resource
    private RedisCacheUtil redisCacheUtil;

    /**
     * @Description: 创建 token
     * @param loginUserVo
     * @return String
     * @throws
     * @Author: bf
     * @Date: 2025/3/24 16:03
     */
    public String createToken(LoginUserVo loginUserVo) {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        // 将 UUID 存储到登录用户中，可以在后台系统中根据 token 值获取 redis 中的数据
        loginUserVo.setToken(token);
        loginUserVo.setLoginTime(System.currentTimeMillis());
        Map<String, Object> claims = new HashMap<>();
        claims.put("token", token);
        // 存储到 redis 中,key 为 token, value 为 loginUserVo
        insertOrRefreshToken(loginUserVo);
        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // 保存或刷新token
    private void insertOrRefreshToken(LoginUserVo loginUserVo) {
        redisCacheUtil.setCacheObject(CacheConstants.LOGIN_USER_KEY + loginUserVo.getToken(),
                loginUserVo, 30, TimeUnit.MINUTES);
    }

    /**
     * @Description: 解析token
     * @param token
     * @return Claims
     * @throws
     * @Author: bf
     * @Date: 2025/3/24 16:03
     */
    public Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    public LoginUserVo getLoginUser(HttpServletRequest request) {
        // 从请求头中获取 token
        String jwtToken = request.getHeader(tokenHeader);
        if (jwtToken == null) {
            return null;
        }
        Claims claims = parseToken(jwtToken);
        String token = (String) claims.get("token");
        // 从 redis 中获取登录用户
        LoginUserVo loginUserVo = (LoginUserVo) redisCacheUtil.getCacheObject(CacheConstants.LOGIN_USER_KEY + token);
        long currentTimeMillis = System.currentTimeMillis();
        Long loginTimeMillis = loginUserVo.getLoginTime();
        // 如果登录时间距当前时间超过20分钟，则不需要刷新token
        if (currentTimeMillis - loginTimeMillis > 20 * 60 * 1000) {
            insertOrRefreshToken(loginUserVo);
        }

        return loginUserVo;
    }
}
