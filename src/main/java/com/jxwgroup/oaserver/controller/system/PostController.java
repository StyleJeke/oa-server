package com.jxwgroup.oaserver.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxwgroup.oaserver.annotation.CurrentUserInfo;
import com.jxwgroup.oaserver.entity.system.Post;
import com.jxwgroup.oaserver.entity.system.User;
import com.jxwgroup.oaserver.service.system.PostService;
import com.jxwgroup.oaserver.vo.DataChangeLogResult;
import com.jxwgroup.oaserver.vo.Result;
import com.jxwgroup.oaserver.vo.system.FindOperate;
import com.jxwgroup.oaserver.vo.system.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
 *  前端控制器
 * </p>
 *
 * @author 追梦
 * @since 2023-11-28
 */
@RestController
@RequestMapping("/system/post")
public class PostController extends ResultController {

    @Autowired
    PostService postService;

    @Operation(method = "分页查询")
    @RequestMapping(value = "/pageFind", method = RequestMethod.POST, produces = "application/json; utf-8")
    public Result<IPage<Post>> pageFind(@RequestBody FindOperate<Post> findOperate, @CurrentUserInfo @Parameter(hidden = true) UserDTO userDTO){
        return setSuccess("成功",postService.pageFind(findOperate,userDTO));
    }

    @RequestMapping(value = "/operate", method = RequestMethod.POST, produces = "application/json; utf-8")
    public Result<Object> operate(@RequestBody Post post, @CurrentUserInfo @Parameter(hidden = true) UserDTO userDTO){
        postService.operate(post,userDTO);
        return setSuccess("成功",null);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json; utf-8")
    public Result<Object> delete(@RequestBody List<String> idList, @CurrentUserInfo @Parameter(hidden = true) UserDTO userDTO){
        postService.delete(idList,userDTO);
        return setSuccess("成功",null);
    }
}
