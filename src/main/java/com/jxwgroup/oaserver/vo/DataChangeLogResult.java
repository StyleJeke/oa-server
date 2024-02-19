package com.jxwgroup.oaserver.vo;

import com.jxwgroup.oaserver.vo.system.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "数据修改日志统一返回对象,如果Response.result返回对象是此类型,则result最终将直接等于对象的result返回结果")
public class DataChangeLogResult<T> {

    @Schema(description = "业务返回对象")
    private T result;

    @Schema(description = "数据日志对象")
    private Object logData;

    @Schema(description = "操作人")
    private UserDTO userDTO;

}
