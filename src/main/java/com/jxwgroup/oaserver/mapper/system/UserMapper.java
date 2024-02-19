package com.jxwgroup.oaserver.mapper.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxwgroup.oaserver.entity.system.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxwgroup.oaserver.vo.system.FindOperate;
import com.jxwgroup.oaserver.vo.system.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 员工用户表 Mapper 接口
 * </p>
 *
 * @author 追梦
 * @since 2023-11-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    Page<UserDTO> pageFind(Page<UserDTO> page, @Param("findOperate")FindOperate<UserDTO> findOperate);

    User findByUsername(@Param("username") String username);

    Integer countCheck(@Param("username") String username, @Param("id") String id);

    void deleteById(@Param("ids") String ids);

}
