package com.jxwgroup.oaserver.mapper.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxwgroup.oaserver.entity.system.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxwgroup.oaserver.vo.system.FindOperate;
import com.jxwgroup.oaserver.vo.system.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 追梦
 * @since 2023-11-28
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

    @SelectProvider(type = PostProvider.class, method = "find")
    IPage<Post> pageFind(Page<Post> page, @Param("findOperate")FindOperate<Post> findOperate, @Param("user")UserDTO user);

    void deleteById(@Param("id") String id);

    class PostProvider{
        public String find(Map<String,Object> params){
            FindOperate<Post> findOperate = (FindOperate<Post>) params.get("findOperate");
            String sql = " select * from sys_post";
            StringBuilder where = new StringBuilder();
            where.append(" where id is not null ");
            if(findOperate.getData() != null){
                if(findOperate.getData().getRoleEnable() != null){
                    where.append(" and role_enable = ${findOperate.data.roleEnable} ");
                }
            }
            return sql + where + " order by code ";
        }
    }
}
