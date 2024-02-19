package com.jxwgroup.oaserver.entity.system;

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
 * 
 * </p>
 *
 * @author 追梦
 * @since 2023-11-28
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_post")
@Schema(name = "Post", description = "$!{table.comment}")
public class Post extends ParentLog<Post> {

    @Schema(description = "岗位英文名缩写")
    @TableField("code")
    private String code;

    @Schema(description = "岗位中文名")
    @TableField("name")
    private String name;

    @Schema(description = "是否启用")
    @TableField("role_enable")
    private Boolean roleEnable;

    @Schema(description = "角色列表")
    @TableField(exist = false)
    private List<String> roleList;

    @Schema(description = "菜单列表")
    @TableField(exist = false)
    private List<String> menus;

    @Override
    public Serializable pkVal() {
        return null;
    }
}
