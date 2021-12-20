package com.qitai.utils.json;

public enum CodeEnum {
    /**
     * 提示
     */
    Prompt(1),
    /**
     * 成功
     */
    Success(0),
    /**
     * 失败
     */
    Fail(-1),
    /**
     * 系统错误
     */
    SystemError(-199),
    /**
     * 微信重定向
     */
    WechatRedirect(403),
    /**
     * 一窗重定向
     */
    WindowRedirect(406),
    /**
     * 参数错误（参数为空，格式不正确）
     */
    ArgumentError(-10001),
    /**
     * 代码字典未配置错误
     */
    CodeDictNullError(-30001),
    /**
     * 代码字段配置值错误
     */
    CodeDictValueError(-30002),
    /**
     * 请求地址未配置错误（没有配置请求地址）
     */
    UrlNullError(-40001),
    /**
     * 请求无法正常访问错误（4开头状态码,无法连接，连接超时等情况）
     */
    UnreachableError(-40002),
    /**
     * 请求出错错误（3或者5开头状态码）
     */
    RequestError(-40003),
    /**
     * 请求成功（2开头状态码）
     */
    RequestSuccess(40000);

    private final int code;

    CodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
