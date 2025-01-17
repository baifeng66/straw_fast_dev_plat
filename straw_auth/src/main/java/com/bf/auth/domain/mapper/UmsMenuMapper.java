package com.bf.auth.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bf.auth.domain.entity.UmsMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: bf
 * @time: 2025/1/16 20:07
 * @description: 菜单交互层
 */
@Mapper
public interface UmsMenuMapper extends BaseMapper<UmsMenu> {
}
