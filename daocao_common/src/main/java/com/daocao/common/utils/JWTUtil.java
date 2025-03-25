package com.daocao.common.utils;

import cn.hutool.core.lang.UUID;
import com.daocao.common.domain.vo.LoginUserVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: daocao
 * @Description: JWT工具类, 通过jwt生成token和解析token，刷新token
 * @Author: bf
 * @Date: 2025/3/24 15:40:26
 * @Version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTUtil {

    @Getter
    @Setter
    private String secret;  // 密钥

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
        Map<String, Object> claims = new HashMap<>();
        claims.put("token", token);
        // todo 存储到 redis 中,key 为 token, value 为 loginUserVo
        claims.put("loginUserVo", loginUserVo);
        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
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


}
