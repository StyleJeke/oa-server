package com.jxwgroup.oaserver.service.global;

import com.jxwgroup.oaserver.config.ProjectConfig;
import com.jxwgroup.oaserver.util.GsonUtil;
import com.jxwgroup.oaserver.util.NetUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NetService {

    @Autowired
    ProjectConfig config;


    @SneakyThrows
    public String findCity(){
        String externalIP = NetUtil.getExternalIP();
        String cityFromIP = NetUtil.getCityFromIP(externalIP, config.getAggregateKey());
        Map<String, Object> map = GsonUtil.toMap(cityFromIP);
        return String.valueOf(GsonUtil.toMap(map.get("result")).get("City"));
    }

    @SneakyThrows
    public Map<String,Object> findWeather(String city){
        return NetUtil.getWeatherInfo(URLEncoder.encode(city, StandardCharsets.UTF_8), config.getWeatherKey());
    }
}
