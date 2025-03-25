package com.daocao.common.service;

import com.daocao.common.domain.dto.LoginDto;

/**
 * @Project: daocao
 * @Description: TODO
 * @Author: bf
 * @Date: 2025/3/23 23:31:18
 * @Version: 1.0
 */
public interface IAuthService {
    String login(LoginDto dto);
}
