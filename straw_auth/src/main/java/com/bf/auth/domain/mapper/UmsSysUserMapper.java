package com.bf.auth.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bf.auth.domain.entity.UmsSysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: bf
 * @time: 2025/1/16 20:05
 * @description: 用户交互层
 */
@Mapper
public interface UmsSysUserMapper extends BaseMapper<UmsSysUser> {

}
