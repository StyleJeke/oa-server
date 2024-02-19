package com.jxwgroup.oaserver.config;

import com.jxwgroup.oaserver.annotation.CurrentUserInfo;
import com.jxwgroup.oaserver.entity.global.Token;
import com.jxwgroup.oaserver.entity.system.User;
import com.jxwgroup.oaserver.exception.AuthorizationException;
import com.jxwgroup.oaserver.mapper.global.TokenMapper;
import com.jxwgroup.oaserver.service.global.TokenService;
import com.jxwgroup.oaserver.util.GsonUtil;
import com.jxwgroup.oaserver.vo.system.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    TokenMapper tokenMapper;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(UserDTO.class) &&
                methodParameter.hasParameterAnnotation(CurrentUserInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request =  nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        if(request == null){
            return null;
        }
        String token = request.getHeader("Authorization");
        if(StringUtils.isEmpty(token)){
            throw new AuthorizationException("请先登录!");
        }
        Token byToken = tokenMapper.getByToken(token);
        if(byToken == null){
            throw new AuthorizationException("登录过期,请重新登录!");
        }
        String user = byToken.getUser();
        return GsonUtil.toJavaObject(user,UserDTO.class);
    }

}
