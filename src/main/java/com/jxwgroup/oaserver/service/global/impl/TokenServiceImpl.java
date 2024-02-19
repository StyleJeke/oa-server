package com.jxwgroup.oaserver.service.global.impl;

import com.jxwgroup.oaserver.entity.global.Token;
import com.jxwgroup.oaserver.mapper.global.TokenMapper;
import com.jxwgroup.oaserver.service.global.TokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 追梦
 * @since 2023-12-20
 */
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {

}
