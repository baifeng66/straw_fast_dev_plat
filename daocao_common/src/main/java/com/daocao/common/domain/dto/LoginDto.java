package com.daocao.common.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Project: daocao
 * @Description: TODO
 * @Author: bf
 * @Date: 2025/3/23 21:59:38
 * @Version: 1.0
 */
@Data
@Schema(description = "登录参数")
public class LoginDto implements Serializable {
    @Schema(description = "账号")
    private String account;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "记住我")
    private Integer rememberMe;
}
