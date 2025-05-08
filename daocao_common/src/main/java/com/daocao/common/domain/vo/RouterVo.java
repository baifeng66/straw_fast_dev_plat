package com.daocao.common.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: daocao
 * @Description: TODO
 * @Author: bf
 * @Date: 2025/3/25 19:33:38
 * @Version: 1.0
 */
@Data
public class RouterVo implements Serializable {
    private Long id;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 菜单名
     */
    private String menuName;


    /**
     * 类型：0，目录，1菜单，2：按钮
     */
    private Integer menuType;

    /**
     * 路由路径   umsUser
     */
    private String path;

    /**
     * 组件路径  如：ums/user/index
     */
    private String componentPath;

    /**
     * 图标
     */
    private String icon;

    private List<RouterVo> children = new ArrayList<>();

}
