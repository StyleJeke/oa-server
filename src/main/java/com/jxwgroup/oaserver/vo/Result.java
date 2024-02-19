package com.jxwgroup.oaserver.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "统一返回实体类")
public class Result<T> implements Serializable {

    @Schema(description = "具体响应内容")
    private T data;

    @Schema(description = "响应描述")
    private String msg;

    @Schema(description = "响应码")
    private Integer code;

    @Schema(description = "是否显示继续按钮")
    private Boolean showButton;

    @Schema(description = "表格提示内容")
    private Object tableData;

    @Schema(description = "待确认的KEY")
    private String confirmKey;

}
