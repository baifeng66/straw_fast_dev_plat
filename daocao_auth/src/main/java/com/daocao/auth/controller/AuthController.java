package com.daocao.auth.controller;

import com.daocao.common.domain.dto.LoginDto;
import com.daocao.common.response.DaoCaoResult;
import com.daocao.common.service.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: daocao
 * @Description: TODO
 * @Author: bf
 * @Date: 2025/3/23 21:58:34
 * @Version: 1.0
 */
@RestController
@RequestMapping("auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("sys")
    public DaoCaoResult sysLogin(@RequestBody LoginDto dto) {
        log.info("dto==>{}", dto);
        return DaoCaoResult.success().put("token", authService.login(dto));
    }
}
