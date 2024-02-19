package com.jxwgroup.oaserver.controller.global;

import com.jxwgroup.oaserver.annotation.CurrentUserInfo;
import com.jxwgroup.oaserver.entity.global.Menu;
import com.jxwgroup.oaserver.entity.system.Post;
import com.jxwgroup.oaserver.service.global.MenuService;
import com.jxwgroup.oaserver.vo.Result;
import com.jxwgroup.oaserver.vo.system.UserDTO;
import io.swagger.v3.oas.annotations.Parameter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.jxwgroup.oaserver.controller.ResultController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author 追梦
 * @since 2024-01-24
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends ResultController {

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/operate", method = RequestMethod.POST, produces = "application/json; utf-8")
    public Result<Object> operate(@RequestBody Menu menuList, @CurrentUserInfo @Parameter(hidden = true) UserDTO userDTO){
        menuService.operate(menuList,userDTO);
        return setSuccess("保存成功",null);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json; utf-8")
    public Result<Object> delete(@Param("id") String id, @CurrentUserInfo @Parameter(hidden = true) UserDTO userDTO){
        menuService.removeMenuById(id);
        return setSuccess("删除成功",null);
    }

    @RequestMapping(value = "/findList", method = RequestMethod.POST, produces = "application/json; utf-8")
    public Result<List<Menu>> findList(@CurrentUserInfo @Parameter(hidden = true) UserDTO userDTO){
        return setSuccess("查询成功",menuService.findList());
    }

    @RequestMapping(value = "/getFirstMenu", method = RequestMethod.GET, produces = "application/json; utf-8")
    public Result<List<Map<String,String>>> getFirstMenu(@Param ("search") String search){
        return setSuccess("查询成功",menuService.getFirstMenu(search));
    }

    @RequestMapping(value = "/getUserMenu", method = RequestMethod.POST, produces = "application/json; utf-8")
    public Result<Map<String,Object>> getUserMenu(@CurrentUserInfo @Parameter(hidden = true) UserDTO userDTO){
        return setSuccess("获取路由成功",menuService.getUserRoutes());
    }

}
