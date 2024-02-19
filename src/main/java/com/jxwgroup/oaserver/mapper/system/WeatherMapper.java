package com.jxwgroup.oaserver.mapper.system;

import com.jxwgroup.oaserver.entity.system.Weather;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 追梦
 * @since 2024-01-16
 */
@Mapper
public interface WeatherMapper extends BaseMapper<Weather> {

    String findByCity(@Param("city") String city);

    Weather findByCityWeather(@Param("city") String city);

}
