package com.jxwgroup.oaserver.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxwgroup.oaserver.entity.system.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxwgroup.oaserver.vo.DataChangeLogResult;
import com.jxwgroup.oaserver.vo.system.FindOperate;
import com.jxwgroup.oaserver.vo.system.UserDTO;
import com.jxwgroup.oaserver.vo.system.UserLogin;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 员工用户表 服务类
 * </p>
 *
 * @author 追梦
 * @since 2023-11-28
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询
     */
    Page<UserDTO> pageFind(FindOperate<UserDTO> findOperate, UserDTO userDTO);

    /**
     * 保存
     */
    DataChangeLogResult<Map<String,Object>> operate(List<User> userDTOList, UserDTO userDTO);

    /**
     * 登录
     */
    Map<String,String> login(UserLogin userLogin);

    /**
     * 删除员工
     */
    DataChangeLogResult<Map<String,Object>> delete(List<String> ids, UserDTO userDTO);
}
