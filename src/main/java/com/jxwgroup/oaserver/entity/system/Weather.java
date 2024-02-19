package com.jxwgroup.oaserver.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jxwgroup.oaserver.entity.ParentEntity;
import com.jxwgroup.oaserver.entity.ParentLog;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 追梦
 * @since 2024-01-16
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_weather")
@Schema(name = "Weather", description = "$!{table.comment}")
public class Weather {

    private static final long serialVersionUID = -797372668713068159L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("obs_time")
    private Timestamp obsTime;

    @TableField("temp")
    private Integer temp;

    @TableField("feels_like")
    private Integer feelsLike;

    @TableField("icon")
    private Integer icon;

    @TableField("text")
    private String text;

    @TableField("wind360")
    private String wind360;

    @TableField("wind_dir")
    private String windDir;

    @TableField("wind_scale")
    private String windScale;

    @TableField("wind_speed")
    private String windSpeed;

    @TableField("humidity")
    private String humidity;

    @TableField("precip")
    private String precip;

    @TableField("pressure")
    private String pressure;

    @TableField("vis")
    private String vis;

    @TableField("cloud")
    private String cloud;

    @TableField("dew")
    private String dew;

    @TableField("city")
    private String city;

}
