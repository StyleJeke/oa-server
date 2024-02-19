package com.jxwgroup.oaserver.mapper.global;

import com.jxwgroup.oaserver.entity.global.Token;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 追梦
 * @since 2023-12-20
 */
@Mapper
public interface TokenMapper extends BaseMapper<Token> {

    Token getByToken(@Param("token") String token);

}
