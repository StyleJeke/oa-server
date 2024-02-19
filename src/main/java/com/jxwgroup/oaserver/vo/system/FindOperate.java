package com.jxwgroup.oaserver.vo.system;

import com.jxwgroup.oaserver.vo.PageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FindOperate<T> {

    @Schema(description = "分页对象")
    private PageEntity page;

    @Schema(description = "模糊查询")
    private String searchVal;

    @Schema(description = "查询条件")
    private T data;

}
