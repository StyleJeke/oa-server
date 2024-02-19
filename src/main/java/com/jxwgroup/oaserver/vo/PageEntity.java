package com.jxwgroup.oaserver.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "分页对象")
public class PageEntity {

    @Schema(description = "每页条数")
    private Integer current;

    @Schema(description = "页数")
    private Integer pageSize;

}
