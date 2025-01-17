package com.bf.auth.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bf.auth.domain.entity.UmsRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: bf
 * @time: 2025/1/16 20:06
 * @description: 角色交互层
 */
@Mapper
public interface UmsRoleMapper extends BaseMapper<UmsRole> {
}
