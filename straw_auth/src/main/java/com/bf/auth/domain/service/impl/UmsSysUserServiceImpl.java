package com.bf.auth.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bf.auth.domain.entity.UmsSysUser;
import com.bf.auth.domain.mapper.UmsSysUserMapper;
import com.bf.auth.domain.service.IUmsSysUserService;
import org.springframework.stereotype.Service;

/**
 * @author: bf
 * @time: 2025/1/16 20:30
 * @description: 用户业务实现类
 */
@Service
public class UmsSysUserServiceImpl extends ServiceImpl<UmsSysUserMapper, UmsSysUser> implements IUmsSysUserService {
}
