package com.jxwgroup.oaserver.entity.global;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jxwgroup.oaserver.entity.ParentLog;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

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
 * @since 2023-12-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("gs_token")
@Schema(name = "Token", description = "$!{table.comment}")
public class Token implements Serializable {
    @Serial
    private static final long serialVersionUID = -797372668713068159L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "token")
    @TableField("token")
    private String token;

    @Schema(description = "刷新token")
    @TableField("refresh_token")
    private String refreshToken;

    @Schema(description = "用户对象")
    @TableField("user")
    private String user;

    @Schema(description = "插入时间")
    @TableField("insert_time")
    private Date insertTime;
}
