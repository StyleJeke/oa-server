package com.jxwgroup.oaserver.entity.global;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jxwgroup.oaserver.entity.ParentLog;
import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author 追梦
 * @since 2024-01-24
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("gs_menu")
@Schema(name = "Menu", description = "$!{table.comment}")
public class Menu extends ParentLog<Menu> {

    @Schema(description = "路由名称")
    @TableField("name")
    private String name;

    @Schema(description = "路由地址")
    @TableField("path")
    private String path;

    @Schema(description = "组件")
    @TableField("component")
    private String component;

    @Schema(description = "路由中文名")
    @TableField("title")
    private String title;

    @Schema(description = "国际化")
    @TableField("i18n_title")
    private String i18nTitle;

    @Schema(description = "图标")
    @TableField("icon")
    private String icon;

    @Schema(description = "序号")
    @TableField("display_order")
    private Integer displayOrder;

    @Schema(description = "是否需要访问权限")
    @TableField("requires_auth")
    private Boolean requiresAuth;

    @Schema(description = "保留状态")
    @TableField("keep_alive")
    private Boolean keepAlive;

    @Schema(description = "是否可见")
    @TableField("hidden")
    private Boolean hidden;

    @Schema(description = "父路由id")
    @TableField("parent_id")
    private String parentId;

    @Schema(description = "子路由")
    @TableField(exist = false)
    private List<Menu> children;

    @Schema(description = "序号")
    @TableField(exist = false)
    private Integer order;

    @Schema(description = "信息")
    @TableField(exist = false)
    private Menu meta;

    @Override
    public Serializable pkVal() {
        return null;
    }
}
