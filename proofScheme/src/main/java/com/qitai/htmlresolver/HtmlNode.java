package com.qitai.htmlresolver;

import java.util.List;

public class HtmlNode {
    private String tag;
    private String value;
    private HtmlNode parentNode;
    private List<HtmlNode> childNode;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HtmlNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(HtmlNode parentNode) {
        this.parentNode = parentNode;
    }

    public List<HtmlNode> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<HtmlNode> childNode) {
        this.childNode = childNode;
    }
}
