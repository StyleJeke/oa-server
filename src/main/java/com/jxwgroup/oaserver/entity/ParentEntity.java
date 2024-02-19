package com.jxwgroup.oaserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(name = "对象父类")
public class ParentEntity<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -797372668713068159L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


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
