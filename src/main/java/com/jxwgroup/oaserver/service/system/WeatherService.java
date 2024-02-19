package com.jxwgroup.oaserver.service.system;

import com.jxwgroup.oaserver.entity.system.Weather;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 追梦
 * @since 2024-01-16
 */
public interface WeatherService extends IService<Weather> {

    Weather findWeather();

}
