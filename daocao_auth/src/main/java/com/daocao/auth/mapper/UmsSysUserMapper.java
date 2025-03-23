package com.daocao.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daocao.auth.domain.po.UmsSysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author bf
 * @description 针对表【ums_sys_user(后台用户表)】的数据库操作Mapper
 * @createDate 2025-03-23 08:02:09
 * @Entity com.daocao.auth.domain.po.UmsSysUser
 */
@Mapper
public interface UmsSysUserMapper extends BaseMapper<UmsSysUser> {

}




