package com.jxwgroup.oaserver.service.system.impl;

import com.jxwgroup.oaserver.entity.system.Weather;
import com.jxwgroup.oaserver.mapper.system.WeatherMapper;
import com.jxwgroup.oaserver.service.global.NetService;
import com.jxwgroup.oaserver.service.system.WeatherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 追梦
 * @since 2024-01-16
 */
@Service
public class WeatherServiceImpl extends ServiceImpl<WeatherMapper, Weather> implements WeatherService {

    @Autowired
    NetService netService;

    @Autowired
    WeatherMapper weatherMapper;

    @Override
    public Weather findWeather() {
        return weatherMapper.findByCityWeather(netService.findCity());
    }

}
