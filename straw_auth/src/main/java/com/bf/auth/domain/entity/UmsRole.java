package com.bf.auth.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: bf
 * @time: 2025/1/16 19:53
 * @description: 角色实体模型类
 */
@Data
@TableName("ums_role")
public class UmsRole implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long RoleId;
    @TableField("role_label")
    private String RoleLabel;
    @TableField("role_name")
    private String RoleName;
    @TableField("sort")
    private Integer Sort;
    @TableField("status")
    private Integer Status;
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
    @TableField("remark")
    private String Remark;
    @TableField(value = "create_time")
    private LocalDateTime CreateTime;
    @TableField(value = "creator")
    private String Creator;
    @TableField(value = "updater")
    private String Updater;
    @TableField(value = "update_time")
    private LocalDateTime UpdateTime;
}
