package com.jxwgroup.oaserver.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxwgroup.oaserver.annotation.CurrentUserInfo;
import com.jxwgroup.oaserver.entity.system.Weather;
import com.jxwgroup.oaserver.service.system.WeatherService;
import com.jxwgroup.oaserver.vo.Result;
import com.jxwgroup.oaserver.vo.system.FindOperate;
import com.jxwgroup.oaserver.vo.system.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.jxwgroup.oaserver.controller.ResultController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 追梦
 * @since 2024-01-16
 */
@RestController
@RequestMapping("/weather")
@Tag(name = "天气相关", description = "天气相关")
public class WeatherController extends ResultController {

    @Autowired
    WeatherService weatherService;

    @Operation(method = "查询当前天气信息")
        @RequestMapping(value = "/findWeather", method = RequestMethod.GET, produces = "application/json; utf-8")
    public Result<Weather> findWeather(@CurrentUserInfo @Parameter(hidden = true) UserDTO userDTO){
        return setSuccess("成功",weatherService.findWeather());
    }

}
