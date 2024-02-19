package com.jxwgroup.oaserver.service.system.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxwgroup.oaserver.entity.global.Token;
import com.jxwgroup.oaserver.entity.system.User;
import com.jxwgroup.oaserver.exception.AuthorizationException;
import com.jxwgroup.oaserver.exception.BusinessException;
import com.jxwgroup.oaserver.exception.LoginException;
import com.jxwgroup.oaserver.exception.NotUserException;
import com.jxwgroup.oaserver.mapper.system.UserMapper;
import com.jxwgroup.oaserver.service.global.TokenService;
import com.jxwgroup.oaserver.service.system.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxwgroup.oaserver.util.MD5Util;
import com.jxwgroup.oaserver.util.SqlUtils;
import com.jxwgroup.oaserver.vo.DataChangeLogResult;
import com.jxwgroup.oaserver.vo.system.FindOperate;
import com.jxwgroup.oaserver.vo.system.UserDTO;
import com.jxwgroup.oaserver.vo.system.UserLogin;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * <p>
 * 员工用户表 服务实现类
 * </p>
 *
 * @author 追梦
 * @since 2023-11-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    TokenService tokenService;

    @Override
    public Page<UserDTO> pageFind(FindOperate<UserDTO> findOperate, UserDTO userDTO) {
        return userMapper.pageFind(new Page<>(findOperate.getPage().getCurrent(),findOperate.getPage().getPageSize()),findOperate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataChangeLogResult<Map<String, Object>> operate(List<User> userList, UserDTO userDTO) {
        DataChangeLogResult<Map<String, Object>> dataChangeLogResult = new DataChangeLogResult<>();
        userList.forEach(x -> {
            if(Objects.nonNull(x.getPassword())){
                try {
                    x.setPassword(MD5Util.getEncryptedPwd(x.getPassword()));
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
            if(StringUtils.isEmpty(x.getId())){
                x.setRegDate(new Date());
                x.setRegUsercode(userDTO.getUsername());
                x.setRegUsername(userDTO.getName());
                x.setJoinedDate(new Date());
            }
            x.setEditDate(new Date());
            x.setEditUsercode(userDTO.getUsername());
            x.setEditUsername(userDTO.getName());
        });
        saveOrUpdateBatch(userList);
        for (User user : userList) {
            Integer i = userMapper.countCheck(user.getUsername(), user.getId());
            if(i > 0){
                throw new BusinessException("该用户已存在!");
            }
        }
        dataChangeLogResult.setLogData(userList);
        dataChangeLogResult.setUserDTO(userDTO);
        return dataChangeLogResult;
    }

    @SneakyThrows
    @Override
    public Map<String,String> login(UserLogin userLogin) {
        User user = userMapper.findByUsername(userLogin.getUsername());
        if(user == null){
            throw new NotUserException("用户不存在！");
        }
        if (!MD5Util.validPassword(userLogin.getPassword(),user.getPassword())) {
            throw new LoginException("账号或密码错误！");
        }
        if(user.getTerminationDate() != null){
            throw new BusinessException("亲爱的" + user.getName() + ",您已经从本公司离职,无法登入本系统。");
        }
        user.setPassword(null);
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString().replaceAll("-",""));
        token.setRefreshToken(UUID.randomUUID().toString().replaceAll("-",""));
        token.setUser(new JSONObject(user).toString());
        token.setInsertTime(new Date());
        tokenService.saveOrUpdate(token);
        Map<String,String> map = new HashMap<>();
        map.put("token",token.getToken());
        map.put("refreshToken",token.getRefreshToken());
        return map;
    }

    @Override
    public DataChangeLogResult<Map<String, Object>> delete(List<String> ids, UserDTO userDTO) {
        DataChangeLogResult<Map<String, Object>> dataChangeLogResult = new DataChangeLogResult<>();
        userMapper.deleteById(SqlUtils.listToString(ids));
        dataChangeLogResult.setLogData(ids);
        dataChangeLogResult.setUserDTO(userDTO);
        return dataChangeLogResult;
    }
}
