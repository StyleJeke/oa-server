package com.jxwgroup.oaserver.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxwgroup.oaserver.annotation.CurrentUserInfo;
import com.jxwgroup.oaserver.entity.system.User;
import com.jxwgroup.oaserver.service.system.UserService;
import com.jxwgroup.oaserver.vo.DataChangeLogResult;
import com.jxwgroup.oaserver.vo.Result;
import com.jxwgroup.oaserver.vo.system.FindOperate;
import com.jxwgroup.oaserver.vo.system.UserDTO;
import com.jxwgroup.oaserver.vo.system.UserLogin;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jxwgroup.oaserver.controller.ResultController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 员工用户表 前端控制器
 * </p>
 *
 * @author 追梦
 * @since 2023-11-28
 */
@RestController
@RequestMapping("/system/user")
@Tag(name = "员工相关", description = "员工相关")
public class UserController extends ResultController {

    @Autowired
    UserService userService;

    @Operation(method = "分页查询")
    @RequestMapping(value = "/pageFind", method = RequestMethod.POST, produces = "application/json; utf-8")
    public Result<Page<UserDTO>> pageFind(@RequestBody FindOperate<UserDTO> findOperate,@CurrentUserInfo @Parameter(hidden = true) UserDTO userDTO){
        return setSuccess("成功",userService.pageFind(findOperate,userDTO));
    }

    @RequestMapping(value = "/operate", method = RequestMethod.POST, produces = "application/json; utf-8")
    public Result<DataChangeLogResult<Map<String, Object>>> operate(@RequestBody List<User> userList,@CurrentUserInfo @Parameter(hidden = true) UserDTO userDTO){
        return setSuccess("成功",userService.operate(userList,userDTO));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json; utf-8")
    public Result<DataChangeLogResult<Map<String, Object>>> delete(@RequestBody List<String> idList,@CurrentUserInfo @Parameter(hidden = true) UserDTO userDTO){
        return setSuccess("删除成功",userService.delete(idList,userDTO));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; utf-8")
    public Result<Map<String,String>> login(@RequestBody UserLogin userLogin){
        return setSuccess("登录成功",userService.login(userLogin));
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET, produces = "application/json; utf-8")
    public Result<UserDTO> init(@CurrentUserInfo @Parameter(hidden = true) UserDTO userDTO){
        return setSuccess("查询用户信息成功",userDTO);
    }

}
