package com.bf.auth.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: bf
 * @time: 2025/1/16 19:41
 * @description: 用户实体模型
 */
@Data
@TableName(value = "ums_sys_user")
public class UmsSysUser implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long Id;
    @TableField(value = "username")
    private String UserName;
    @TableField(value = "nickname")
    private String NickName;
    @TableField(value = "email")
    private String Email;
    @TableField(value = "mobile")
    private String Mobile;
    @TableField(value = "sex")
    private Integer Sex;
    @TableField(value = "avatar")
    private String Avatar;
    @TableField(value = "password")
    private String PassWord;
    @TableField(value = "status")
    private Integer Status;
    @TableField(value = "create_time")
    private LocalDateTime CreateTime;
    @TableField(value = "creator")
    private String Creator;
    @TableField(value = "updater")
    private String Updater;
    @TableField(value = "update_time")
    private LocalDateTime UpdateTime;
    @TableField(value = "remark")
    private String Remark;
    @TableLogic(value = "0", delval = "1")
    private Integer Deleted;
}
