package com.jxwgroup.oaserver.mapper.global;

import com.jxwgroup.oaserver.entity.global.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author 追梦
 * @since 2024-01-24
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findList();

    List<Map<String,String>> getFirstMenu(@Param("searchVal") String searchVal);


}
