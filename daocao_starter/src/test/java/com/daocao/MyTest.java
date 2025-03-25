package com.daocao;

import com.daocao.common.utils.JWTUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Project: daocao
 * @Description: TODO
 * @Author: bf
 * @Date: 2025/3/24 15:52:13
 * @Version: 1.0
 */
@SpringBootTest
public class MyTest {
    @Resource
    private JWTUtil jwtUtil;

    @Test
    public void test() {
        System.out.println(jwtUtil.getSecret());
    }
}
