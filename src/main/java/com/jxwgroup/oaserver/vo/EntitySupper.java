package com.jxwgroup.oaserver.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "统一返回实体类")
public class EntitySupper {

    @Schema(description = "主键")
    private String id;

}
