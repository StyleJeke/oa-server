package com.jxwgroup.oaserver.vo.system;

import com.jxwgroup.oaserver.entity.system.User;
import com.jxwgroup.oaserver.util.MD5Util;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Data
@Schema(name = "员工登录、查看")
public class UserDTO {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "所属部门")
    private String department;

    @Schema(description = "手机号")
    private Long phone;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "性别")
    private String sex;

    @Schema(description = "家庭地址")
    private String familyAddress;

    @Schema(description = "紧急联系电话")
    private Integer sosPhone;

    @Schema(description = "是否领导")
    private Boolean isLeader;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "用户状态")
    private String userStatus;

    @Schema(description = "主键")
    private String id;

    @Schema(description = "密码")
    private String password;

}
