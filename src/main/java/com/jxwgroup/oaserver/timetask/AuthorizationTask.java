package com.jxwgroup.oaserver.timetask;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxwgroup.oaserver.entity.global.Token;
import com.jxwgroup.oaserver.entity.system.Weather;
import com.jxwgroup.oaserver.mapper.system.WeatherMapper;
import com.jxwgroup.oaserver.service.global.NetService;
import com.jxwgroup.oaserver.service.global.TokenService;
import com.jxwgroup.oaserver.service.system.WeatherService;
import com.jxwgroup.oaserver.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@EnableScheduling
@EnableAsync
@Component
@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "scheduled", name = "enable", havingValue = "true")
public class AuthorizationTask {

    @Autowired
    TokenService tokenService;

    @Autowired
    NetService netService;

    @Autowired
    WeatherService weatherService;

    @Autowired
    WeatherMapper weatherMapper;

//    @Async
//    @Scheduled(fixedRate = 7 * 24 * 60 * 60 * 1000)
    public void findAuthorization(){
        List<Token> list = tokenService.list();
        for (Token token : list) {
            long time = new Date().getTime();
            long millisInThirtyDay =  7L * 24L * 60L * 60L * 1000L;
            if (token.getInsertTime().getTime() < (time - millisInThirtyDay)) {
                tokenService.removeById(token);
            }
        }
    }

    @Async
    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void findWeather(){
        String city = netService.findCity();
        Map<String, Object> weather = netService.findWeather(city);
        if(Objects.equals("200",weather.get("code")) && !Objects.isNull(weather.get("now"))){
            Weather now = GsonUtil.toJavaObject(weather.get("now"), Weather.class);
            now.setCity(city);
            String weatherId = weatherMapper.findByCity(city);
            if(StringUtils.isNotEmpty(weatherId)){
                now.setId(weatherId);
                weatherService.saveOrUpdate(now);
                return;
            }
            weatherService.saveOrUpdate(now);
        }
    }
}
