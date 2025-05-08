package com.daocao.sysuser.controller;

import com.daocao.common.domain.vo.RouterVo;
import com.daocao.common.response.DaoCaoResult;
import com.daocao.common.service.UmsMenuService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Project: daocao
 * @Description: 菜单控制器
 * @Author: bf
 * @Date: 2025/3/25 16:36:37
 * @Version: 1.0
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @Resource
    private UmsMenuService umsMenuService;

    @GetMapping("self")
    public DaoCaoResult searchSelfMenu() {
        List<RouterVo> menuList = umsMenuService.searchSelfMenu();
        return DaoCaoResult.success(menuList);
    }
}
