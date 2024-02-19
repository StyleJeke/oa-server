package com.jxwgroup.oaserver;

import com.jxwgroup.oaserver.service.global.MenuService;
import com.jxwgroup.oaserver.service.global.NetService;
import com.jxwgroup.oaserver.service.system.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OaServerApplicationTests {

    @Autowired
    NetService netService;

    @Autowired
    WeatherService weatherService;

    @Autowired
    MenuService menuService;

    @Test
    void contextLoads() {

//        System.out.println(GsonUtil.toJSONString(menuService.findList()));
        //保存
        menuService.demo();
    }



}
