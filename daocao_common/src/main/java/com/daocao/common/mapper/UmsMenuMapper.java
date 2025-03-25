package com.daocao.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daocao.common.domain.po.UmsMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author bf
 * @description 针对表【ums_menu】的数据库操作Mapper
 * @createDate 2025-03-23 08:08:51
 * @Entity com.daocao.auth.domain.po.UmsMenu
 */
@Mapper
public interface UmsMenuMapper extends BaseMapper<UmsMenu> {

    List<UmsMenu> selectByRoleIds(@Param("roleIds") List<Long> roleIds);
}




