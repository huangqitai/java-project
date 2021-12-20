package com.qitai.utils.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qitai.utils.CheckUtil;
import com.qitai.utils.ConvertUtil;
import com.qitai.utils.json.CodeEnum;

/**
 * 接口返回对象
 * 示例：JsonReturn<Test> jsonReturn = new JsonReturn<>();
 * if (true) {
 * Test test = new Test();
 * test.setId(1);
 * test.setName("测试");
 * jsonReturn.setResult(test);
 * } else {
 * jsonReturn.setFail();
 * //自动设置setMsg(异常提示信息，如："运行出错")
 * jsonReturn.setException(new RuntimeException("运行出错"));
 * }
 *
 * @param <T>
 */
public class JsonReturn<T> {
    /**
     * 返回结果代码
     */
    private int code = 0;
    /**
     * 返回结果
     */
    private T result;
    /**
     * 提示信息
     */
    private String msg = "";
    /**
     * 异常
     */
    private Exception exception;

    /**
     * 默认构造函数（成功）
     */
    public JsonReturn() {
    }

    /**
     * 构造成功结果
     *
     * @param msg    提示信息
     * @param result 返回内容
     */
    public JsonReturn(String msg, T result) {
        setMsg(msg);
        setResult(result);
    }

    /**
     * 构造异常结果
     *
     * @param e 异常
     */
    public JsonReturn(Exception e) {
        setFail();
        setException(e);
    }

    /**
     * 构造其他结果
     *
     * @param codeEnum 代码枚举类
     * @param msg      提示信息
     */
    public JsonReturn(CodeEnum codeEnum, String msg) {
        setCode(codeEnum.getCode());
        setMsg(msg);
    }

    /**
     * 获取提示消息（可以正确的提示消息，也可以是错误消息）
     *
     * @return 提示信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置提示消息（可以正确的提示消息，也可以是错误消息）
     *
     * @param msg 提示信息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取处理结果代码。作为规范，用0表示成功，其他值表示具体错误
     *
     * @return 处理结果的代码。
     */
    public int getCode() {
        return this.code;
    }

    /**
     * 设置处理结果的代码。作为规范，用0表示成功，其他值表示具体错误
     *
     * @param code 要设置的代码
     */
    @Deprecated
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 处理结果
     *
     * @return 如果没有，返回{@code null}。
     */
    public T getResult() {
        return this.result;
    }

    /**
     * 设置处理结果
     *
     * @param result 要设置的处理结果
     */
    public void setResult(T result) {
        this.result = result;
    }

    public Exception getException() {
        return exception;
    }

    /**
     * 设置异常信息记录到mongoDb日志，最终返回结果不含此异常信息（此属性只为记录异常日志使用）
     *
     * @param exception 异常对象
     */
    @JsonIgnore
    public void setException(Exception exception) {
        if (exception != null) {
            String message;
            if (CheckUtil.isNullorEmpty(exception.getMessage())) {
                if (exception.getCause() != null) {
                    message = exception.getCause().getMessage();
                }else{
                    message = ConvertUtil.exceptionToString(exception);
                }
            } else {
                message = exception.getMessage();
            }
            this.msg = message;
        }
        this.exception = exception;
    }

    /**
     * 设置成功代码
     */
    public void setSuccess() {
        this.code = CodeEnum.Success.getCode();
    }

    /**
     * 设置提示代码
     */
    public void setPrompt() {
        this.code = CodeEnum.Prompt.getCode();
    }

    /**
     * 设置出错代码
     */
    public void setFail() {
        this.code = CodeEnum.Fail.getCode();
    }

    /**
     * 设置系统错误代码（系统代码无法处理的错误，例如服务器没有部署，数据库宕机，子系统断电等极端情况）
     */
    public void setSystemError() {
        this.code = CodeEnum.SystemError.getCode();
    }

    /**
     * 设置参数错误代码（参数为空，格式不正确）
     */
    public void setArgumentError() {
        this.code = CodeEnum.ArgumentError.getCode();
    }

    /**
     * 设置代码字典未配置错误代码
     */
    public void setCodeDictNullError() {
        this.code = CodeEnum.CodeDictNullError.getCode();
    }

    /**
     * 设置代码字段配置值错误代码
     */
    public void setCodeDictValueError() {
        this.code = CodeEnum.CodeDictValueError.getCode();
    }

    /**
     * 设置请求地址未配置错误代码（没有配置请求地址）
     */
    public void setUrlNullError() {
        this.code = CodeEnum.UrlNullError.getCode();
    }

    /**
     * 设置请求无法正常访问错误代码（4开头状态码,无法连接，连接超时等情况）
     */
    public void setUnreachableError() {
        this.code = CodeEnum.UnreachableError.getCode();
    }

    /**
     * 设置请求出错错误代码（3或者5开头状态码）
     */
    public void setRequestError() {
        this.code = CodeEnum.RequestError.getCode();
    }

    /**
     * 设置请求成功代码（2开头状态码）
     */
    public void setRequestSuccess() {
        this.code = CodeEnum.RequestSuccess.getCode();
    }
}
