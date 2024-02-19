package com.jxwgroup.oaserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Schema(name = "保存日志对象父类")
public class ParentLog<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -797372668713068159L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "创建人code")
    @TableField("reg_usercode")
    private String regUsercode;

    @Schema(description = "创建人名称")
    @TableField("reg_username")
    private String regUsername;

    @Schema(description = "创建时间")
    @TableField("reg_date")
    private Date regDate;

    @Schema(description = "修改人code")
    @TableField("edit_usercode")
    private String editUsercode;

    @Schema(description = "修改人名称")
    @TableField("edit_username")
    private String editUsername;

    @Schema(description = "修改时间")
    @TableField("edit_date")
    private Date editDate;

    @Schema(description = "原始数据对象")
    @TableField(exist = false)
    private T oldData;

    @Schema(description = "是否为删除数据")
    @TableField(exist = false)
    private Boolean isRemoveData;


    public boolean isRemove(){
        return isRemoveData!=null && isRemoveData;
    }

    public Serializable pkVal() {
        return null;
    }
}
