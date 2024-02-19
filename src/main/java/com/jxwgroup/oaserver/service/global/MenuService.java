package com.jxwgroup.oaserver.service.global;

import com.jxwgroup.oaserver.entity.global.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxwgroup.oaserver.vo.system.UserDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author 追梦
 * @since 2024-01-24
 */
public interface MenuService extends IService<Menu> {

    List<Menu> findList();

    void operate(Menu allMenus, UserDTO userDTO);

    /**
     * 删除菜单，并且删除底下所有子菜单
     */
    void removeMenuById(String id);

    /**
     * 获取一级菜单
     */
    List<Map<String,String>> getFirstMenu(String search);


    /**
     * 动态路由获取
     */
    Map<String,Object> getUserRoutes();


    List<String> demo();

}
