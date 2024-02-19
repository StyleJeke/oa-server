package com.jxwgroup.oaserver.service.system.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxwgroup.oaserver.entity.global.RolePerm;
import com.jxwgroup.oaserver.entity.system.Post;
import com.jxwgroup.oaserver.mapper.global.RolePermMapper;
import com.jxwgroup.oaserver.mapper.system.PostMapper;
import com.jxwgroup.oaserver.service.global.RolePermService;
import com.jxwgroup.oaserver.service.system.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxwgroup.oaserver.util.SqlUtils;
import com.jxwgroup.oaserver.vo.system.FindOperate;
import com.jxwgroup.oaserver.vo.system.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 追梦
 * @since 2023-11-28
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Autowired
    PostMapper postMapper;

    @Autowired
    RolePermService rolePermService;

    @Autowired
    RolePermMapper rolePermMapper;

    @Override
    public IPage<Post> pageFind(FindOperate<Post> findOperate, UserDTO userDTO) {
        IPage<Post> pageFind = postMapper.pageFind(new Page<>(findOperate.getPage().getCurrent(), findOperate.getPage().getPageSize()), findOperate, userDTO);
        List<Post> records = pageFind.getRecords();
        if(CollectionUtils.isEmpty(records)){
            return pageFind;
        }
        for (Post record : records) {
            record.setMenus(rolePermMapper.findByRoleId(record.getId()));
        }
        pageFind.setRecords(records);
        return pageFind;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void operate(Post post, UserDTO userDTO) {
        if(StringUtils.isEmpty(post.getId())){
            post.setRegDate(new Date());
            post.setRegUsercode(userDTO.getUsername());
            post.setRegUsername(userDTO.getName());
        }
        post.setEditDate(new Date());
        post.setEditUsercode(userDTO.getUsername());
        post.setEditUsername(userDTO.getName());
        saveOrUpdate(post);
        //删除关系图
        rolePermMapper.deleteByRoleId(post.getId());
        //保存关系图
        List<RolePerm> list = new ArrayList<>();
        for (String menuId : post.getMenus()) {
            RolePerm rolePerm = new RolePerm();
            rolePerm.setRoleId(post.getId());
            rolePerm.setMenuId(menuId);
            list.add(rolePerm);
        }
        rolePermService.saveOrUpdateBatch(list);
    }

    @Override
    public void delete(List<String> idList, UserDTO userDTO) {
        postMapper.deleteById(SqlUtils.listToString(idList));
    }

}
