package com.daocao.common.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.common.constants.CacheConstants;
import com.daocao.common.domain.po.UmsMenu;
import com.daocao.common.domain.po.UmsRole;
import com.daocao.common.domain.vo.LoginUserVo;
import com.daocao.common.domain.vo.RouterVo;
import com.daocao.common.mapper.UmsMenuMapper;
import com.daocao.common.service.UmsMenuService;
import com.daocao.common.utils.RedisCacheUtil;
import com.daocao.common.utils.SecurityUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bf
 * @description 针对表【ums_menu】的数据库操作Service实现
 * @createDate 2025-03-23 08:08:51
 */
@Service
@Slf4j
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenu>
        implements UmsMenuService {

    @Resource
    private UmsMenuMapper umsMenuMapper;
    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Override
    public List<RouterVo> searchSelfMenu() {
        // 查询出当前用户的角色信息
        String token = SecurityUtil.getLoginUserVo().getToken();
        LoginUserVo loginUserVo = (LoginUserVo) redisCacheUtil.getCacheObject(CacheConstants.LOGIN_USER_KEY + token);
        List<Long> roleIds = loginUserVo.getUmsSysUser().getRoles().stream().map(UmsRole::getRoleId).toList();
        log.info("roleIds: " + roleIds);
        List<UmsMenu> umsMenus = umsMenuMapper.selectByRoleIds(roleIds);
        log.info("umsMenus: " + umsMenus);
        // 通过递归查询子菜单，构造树形结构
        List<RouterVo> routerVos = getRouter(umsMenus);
        log.info("routerVos: " + routerVos);
        return routerVos;
    }

    // 获取路由
    private List<RouterVo> getRouter(List<UmsMenu> umsMenus) {
        List<RouterVo> routerVoList = new ArrayList<>();
        // 1. 先找出所有一级菜单
        List<UmsMenu> parentMenuList = umsMenus.stream()
                .filter(menu -> menu.getParentId() == 0).toList();
        routerVoList = BeanUtil.copyToList(parentMenuList, RouterVo.class);
        // 2. 遍历一级菜单，找出其子菜单
        for (RouterVo routerVo : routerVoList) {
            List<RouterVo> childrenMenuList = buildTree(umsMenus, routerVo.getId());
            routerVo.setChildren(childrenMenuList);
        }
        return routerVoList;
    }

    // 递归获取所有子菜单
    private List<RouterVo> buildTree(List<UmsMenu> umsMenus, Long id) {
        List<RouterVo> childrenList = new ArrayList<>();
        // 找出所有子菜单，并且转换为RouterVo
        umsMenus.stream().filter(menu -> menu.getParentId().equals(id)).forEach(childMenu -> {
            childrenList.add(BeanUtil.copyProperties(childMenu, RouterVo.class));
        });
        // 递归获取子菜单的子菜单
        for (RouterVo child : childrenList) {
            child.setChildren(buildTree(umsMenus, child.getId()));
        }
        return childrenList;
    }
}




