package com.bf.auth.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: bf
 * @time: 2025/1/16 20:00
 * @description: 菜单实体模型类
 */
@Data
@TableName("ums_menu")
public class UmsMenu implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long Id;
    @TableField(value = "parent_id")
    private Integer ParentId;
    @TableField(value = "menu_name")
    private String MenuName;
    @TableField(value = "sort")
    private Integer Sort;
    @TableField(value = "menu_type")
    private Integer MenuType;
    @TableField(value = "path")
    private String Path;
    @TableField("component_path")
    private String ComponentPath;
    @TableField("perms")
    private String Perms;
    @TableField("icon")
    private String Icon;
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
    @TableLogic(value = "0", delval = "1")
    private Integer Deleted;
}
