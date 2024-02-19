package com.jxwgroup.oaserver.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxwgroup.oaserver.entity.system.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxwgroup.oaserver.vo.system.FindOperate;
import com.jxwgroup.oaserver.vo.system.UserDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 追梦
 * @since 2023-11-28
 */
public interface PostService extends IService<Post> {

    /**
     * 岗位分页查询
     */
    IPage<Post> pageFind(FindOperate<Post> findOperate, UserDTO userDTO);


    /**
     * 保存岗位
     */
    void operate(Post postList, UserDTO userDTO);

    /**
     * 删除岗位
     */
    void delete(List<String> idList, UserDTO userDTO);

}
