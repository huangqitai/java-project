package com.qitai.xmlparse;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "R")
public class XsjgResponseVo {
    /**
     * <R><code>0</code><msg>正确。本手业务未关联至对应主产权。</msg><data>HJ-20220506180319415-Y96JJ1VY</data></R>
     */
    private String code;
    private String msg;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
