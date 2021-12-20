package com.qitai.utils;


/**
 * 系统需要的一些常量定义
 *
 * @author dennis
 */
public final class Constant {

    /**
     * 提示中文
     */
    public static final String ERROR_OF_NONE = "未知错误";

    /**
     * 提示中文
     */
    public static final String UNKNOWN = "(未知)";

    /**
     * 错误标准提示信息。第一个位置为服务名，第二个位置为错误信息。
     */
    public static final String ERROR_PROMPT = "%s 服务拒绝了本次调用：%s。%n如此问题一直出现，请联系管理员。";

    /**
     * 系统内的默认编码方式
     */
    public static final String DEFAULT_ENCODE = "UTF-8";

    /**
     * 真的
     */
    public static final String TRUE = "true";
    /**
     * 假的
     */
    public static final String FALSE = "false";
    /**
     * 大写真的
     */
    public static final String UPPER_TRUE = "TRUE";
    /**
     * 大写假的
     */
    public static final String UPPER_FALSE = "FALSE";

    /**
     * 成功的
     */
    public static final String SUCCESS = "success";

    /**
     * 错误的
     */
    public static final String ERROR = "error";

    /**
     * post请求方式
     */
    public static final String CONTENT_TYPE_HTML = "text/html";

    /**
     * post请求方式
     */
    public static final String CONTENT_TYPE_JSON = "text/json";
    /**
     * post请求方式
     */
    public static final String CONTENT_TYPE_XML = "text/xml";
    /**
     * post请求方式
     */
    public static final String CONTENT_TYPE_PLAIN = "text/plain";

    /**
     * 系统默认分页记录数
     * 默认每页显示的条数
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 跨域调用的客户端回调方法前缀
     */
    public static final String DEFAULT_CALLBACK = "ibase2-xjp-cb";

    /**
     * http请求连接超时(毫秒)
     */
    public static final int HTTP_CONNTION_TIMEOUT = 15000;
    /**
     * http读数据超时(毫秒)
     */
    public static final int HTTP_READ_TIMEOUT = 300000;

    /**
     * 短信发送缓存前缀
     */
    public static final String CACHE_ITEM_PHONE_KEY = "weChat-service-confirm-phone-";

    /**
     * 字符串默认值0
     */
    public final static String ZERO = "0";

    /**
     * 数字默认值0
     */
    public final static Integer ZERO_INT = 0;

    /**
     * 字符串默认值1
     */
    public final static String ONE = "1";

    /**
     * 数字默认值1
     */
    public final static Integer ZERO_ONE = 1;

    /**
     * 字符串默认值2
     */
    public final static String TWO = "2";

    /**
     * 数字默认值2
     */
    public final static Integer ZERO_TWO = 2;

    /**
     *字符串默认值3
     */
    public final static String THREE = "3";

    /**
     * 数字默认值3
     */
    public final static Integer ZERO_THREE = 3;

    /**
     * 为了符合cacheService key的名称定义格式。
     */
    public final static String CSUFFIX_USERLOGIN = "_2ae941e8-21e5-4013-9568-4dcee975333a";

    /**
     * 成功
     */
    public final static String TWO_HUNDRED = "200";


    public final static String GET = "GET";

    public final static String MULTIPART_FORM = "MULTIPART_FORM";

    public final static String FORM = "FORM";

    public final static String APPLICATION_JSON = "APPLICATION_JSON";

    public final static String APPLICATION_XML = "APPLICATION_XML";

    public final static String TEXT_XML = "TEXT_XML";

    public final static String BEFORE_REQUEST_ERROR_TYPE = "未知的请求对象类型，请增加对应的发送请求";

    /**
     * 佛山XML模板
     */
    public static String XML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sdfw=\"http://sdfw.Sdservices.sdxm.PrjCustomFolder.EstateSys/\">" +
            "   <soapenv:Header/>" +
            "   <soapenv:Body>" +
            "      <sdfw:?2>" +
            "         <strJson>?1</strJson>" +
            "      </sdfw:?2>" +
            "   </soapenv:Body>" +
            "</soapenv:Envelope>";
}
