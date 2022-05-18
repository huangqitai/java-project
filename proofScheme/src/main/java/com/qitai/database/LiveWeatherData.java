package com.qitai.database;


import lombok.Data;

import java.util.Date;

/**
 * 天气实况
 */
@Data
public class LiveWeatherData {
    /**
     * 区域编码
     * 区域名称
     * 天气状态
     * 温度
     * 相对湿度
     * 气压
     * 一小时降雨量
     * 天气图标
     * 实况时间
     * 风向（度）
     * 风速（米/秒）
     * 风速等级
     * AQI
     * PM2.5
     */
    private String bsm;
    private Date hqsj;

    private String r;
    private String n;
    private String sk_s;
    private String sk_t;
    private String sk_h;
    private String sk_p;
    private String sk_r1h;
    private String sk_i;
    private String sk_time;
    private String sk_wd;
    private String sk_wp;
    private String sk_wp_level;
    private String pm_aqi;
    private String pm_pm25;
}
