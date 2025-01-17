package com.bf.auth.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bf.auth.domain.entity.UmsRole;
import com.bf.auth.domain.mapper.UmsRoleMapper;
import com.bf.auth.domain.service.IUmsRoleService;
import org.springframework.stereotype.Service;

/**
 * @author: bf
 * @time: 2025/1/16 20:34
 * @description: 角色业务实现类
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService  {
}
