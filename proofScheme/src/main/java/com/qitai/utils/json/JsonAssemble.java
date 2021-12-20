package com.qitai.utils.json;

import com.qitai.utils.ConvertUtil;
import com.qitai.utils.json.JsonReturn;

/**
 * @author 胡现荣
 * @date 2020-05-27
 */
public class JsonAssemble {

    /**
     * 提示调用
     *
     * @param msg 提示信息
     * @return
     */
    public static JsonReturn setFail(String msg) {
        JsonReturn jsonReturn = new JsonReturn();
        jsonReturn.setPrompt();
        jsonReturn.setMsg(msg);
        return jsonReturn;
    }
    /**
     * 失败调用
     *
     * @param e 异常信息
     * @return
     */
    public static JsonReturn setError(Exception e) {
        JsonReturn jsonReturn = new JsonReturn();
        jsonReturn.setFail();
        jsonReturn.setMsg(ConvertUtil.exceptionToString(e));
        jsonReturn.setException(e);
        return jsonReturn;
    }

}
