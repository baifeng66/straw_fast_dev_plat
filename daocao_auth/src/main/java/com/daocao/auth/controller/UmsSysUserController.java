package com.daocao.auth.controller;

import cn.hutool.core.util.BooleanUtil;
import com.daocao.common.domain.po.UmsSysUser;
import com.daocao.common.response.DaoCaoResult;
import com.daocao.common.service.impl.UmsSysUserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Project: daocao
 * @Description: TODO
 * @Author: bf
 * @Date: 2025/3/23 10:07:15
 * @Version: 1.0
 */
@RestController
@RequestMapping("ums/sysuser")
@RequiredArgsConstructor
@Tag(name = "系统用户控制器")
public class UmsSysUserController {

    private final UmsSysUserServiceImpl umsSysUserService;

    /**
     * @Description: 新增系统用户
     * @param umsSysUser
     * @return DaoCaoResult<?>
     * @throws
     * @Author: bf
     * @Date: 2025/3/23 10:24
     */
    @PostMapping
    @Operation(summary = "新增系统用户")
    public DaoCaoResult addSysUser(@RequestBody UmsSysUser umsSysUser) {
        boolean saved = umsSysUserService.save(umsSysUser);
        if (BooleanUtil.isTrue(saved)) {
            return DaoCaoResult.success();
        }
        return DaoCaoResult.error();
    }


    @GetMapping("/list")
    @Operation(summary = "查询系统用户列表")
    public DaoCaoResult searchList() {
        return DaoCaoResult.success(umsSysUserService.list());
    }
}
