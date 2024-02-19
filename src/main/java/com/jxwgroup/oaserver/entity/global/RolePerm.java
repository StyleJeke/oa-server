package com.jxwgroup.oaserver.entity.global;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jxwgroup.oaserver.entity.ParentEntity;
import com.jxwgroup.oaserver.entity.ParentLog;
import java.io.Serializable;
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
 * @since 2024-01-04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("gs_role_perm")
@Schema(name = "RolePerm", description = "$!{table.comment}")
public class RolePerm extends ParentEntity<RolePerm> {

    @Schema(description = "角色id")
    @TableField("role_id")
    private String roleId;

    @Schema(description = "菜单id")
    @TableField("menu_id")
    private String menuId;

}
