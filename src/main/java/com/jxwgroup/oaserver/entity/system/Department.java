package com.jxwgroup.oaserver.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jxwgroup.oaserver.entity.ParentLog;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门	
 * </p>
 *
 * @author 追梦
 * @since 2023-11-28
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_department")
@Schema(name = "Department", description = "$!{table.comment}")
public class Department extends ParentLog<Department> {

    @Schema(description = "部门英文缩写")
    @TableField("department_code")
    private String departmentCode;

    @Schema(description = "部门名称")
    @TableField("department_name")
    private String departmentName;

    @Override
    public Serializable pkVal() {
        return null;
    }
}
