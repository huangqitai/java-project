package com.qitai.database;

import lombok.Data;

import java.util.Date;

/**
 * 天气预警
 */
@Data
public class WeatherWarningData {
    /**
     *资料时间
     * 发布站名
     * 入库时间
     * 编报时分
     * 预警发布年
     * 预警发布日
     * 预警站号经度
     * 台风预警级别
     * 台风预警解除时间（年月日时分）
     * 暴雨预警状态
     * 高温预警级别
     * 高温预警解除时间
     * 寒冷预警状态
     * 大雾预警级别
     * 大雾预警解除时间
     * 灰霾预警状态
     * 雷雨大风预警状态
     * 道路结冰预警级别
     * 道路结冰预警解除时间
     * 冰雹预警状态
     * 森林火险预警级别
     * 森林火险预警解除时间
     * 记录序号
     * 编报中心
     * 收到时间
     * 编报日期（年月日）
     * 预警发布日期（年月日）
     * 预警发布月
     * 预警发布时间（时分）
     * 预警站号纬度
     * 台风预警状态
     * 暴雨预警级别
     * 暴雨预警解除时间
     * 高温预警状态
     * 寒冷预警级别
     * 寒冷预警解除时间
     * 大雾预警状态
     * 灰霾预警级别
     * 雷雨大风预警级别
     * 雷雨大风预警解除时间
     * 道路结冰预警状态
     * 冰雹预警级别
     * 冰雹预警解除时间
     * 森林火险预警状态
     * 入库时间
     * 同步序号
     */
    private String bsm;

    private String DDATETIME;
    private String FBCC;
    private Date ITIME;
    private String V_BHM;
    private String C_FYYYY;
    private String C_FDD;
    private String V06001;
    private String V_TFLEV;
    private Date C_TFCLT;
    private String C_BYSTA;
    private String V_GWLEV;
    private Date C_GWCLT;
    private String C_HLSTA;
    private String V_DWLEV;
    private Date C_DWCLT;
    private String C_HMSTA;
    private String C_LYSTA;
    private String V_JBLEV;
    private Date C_JBCLT;
    private String C_BBSTA;
    private String V_SLLEV;
    private Date C_SLCLT;
    private String KEYID;
    private String BBCC;
    private Date RTIME;
    private Date C_BDATE;
    private Date C_FDATE;
    private String C_FMM;
    private String V_FHM;
    private String V05001;
    private String C_TFSTA;
    private String V_BYLEV;
    private Date C_BYCLT;
    private String C_GWSTA;
    private String V_HLLEV;
    private String C_HLCLT;
    private String C_DWSTA;
    private String V_HMLEV;
    private String V_LYLEV;
    private Date C_LYCLT;
    private String C_JBSTA;
    private String V_BBLEV;
    private String C_BBCLT;
    private String C_SLSTA;
    private Date CRTTIME;
    private String sync_rowid;

}
