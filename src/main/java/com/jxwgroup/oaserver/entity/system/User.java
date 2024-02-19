package com.jxwgroup.oaserver.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jxwgroup.oaserver.entity.ParentLog;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 员工用户表
 * </p>
 *
 * @author 追梦
 * @since 2023-11-28
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_user")
@Schema(name = "User", description = "$!{table.comment}")
public class User extends ParentLog<User> {

    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "名字")
    @TableField("name")
    private String name;

    @Schema(description = "年龄")
    @TableField("age")
    private Integer age;

    @Schema(description = "性别")
    @TableField("sex")
    private String sex;

    @Schema(description = "手机号")
    @TableField("phone")
    private Long phone;

    @Schema(description = "紧急联系电话")
    @TableField("sos_phone")
    private Long sosPhone;

    @Schema(description = "家庭地址")
    @TableField("family_address")
    private String familyAddress;

    @Schema(description = "身份证号")
    @TableField("id_card")
    private String idCard;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "入职日期")
    @TableField("joined_date")
    private Date joinedDate;

    @Schema(description = "离职日期")
    @TableField("termination_date")
    private Date terminationDate;

    @Schema(description = "部门名")
    @TableField("department_id")
    private String departmentId;

    @Schema(description = "岗位")
    @TableField("post_id")
    private String postId;

    @Schema(description = "是否部门领导")
    @TableField("is_leader")
    private Boolean isLeader;

    @Schema(description = "头像")
    @TableField("avatar")
    private String avatar;

    @Schema(description = "邮箱")
    @TableField("email")
    private String email;

    @Override
    public Serializable pkVal() {
        return null;
    }
}
