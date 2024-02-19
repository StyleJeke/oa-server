package com.jxwgroup.oaserver.mapper.global;

import com.jxwgroup.oaserver.entity.global.RolePerm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 追梦
 * @since 2024-01-04
 */
@Mapper
public interface RolePermMapper extends BaseMapper<RolePerm> {

    List<String> findByRoleId(@Param("id") String id);

    void deleteByRoleId(@Param("roleId") String roleId);

}
