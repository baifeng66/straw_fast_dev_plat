package com.bf.auth.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bf.auth.domain.entity.UmsMenu;
import com.bf.auth.domain.mapper.UmsMenuMapper;
import com.bf.auth.domain.service.IUmsMenuService;
import org.springframework.stereotype.Service;

/**
 * @author: bf
 * @time: 2025/1/16 20:37
 * @description: 菜单业务实现类
 */
@Service
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenu> implements IUmsMenuService {
}
