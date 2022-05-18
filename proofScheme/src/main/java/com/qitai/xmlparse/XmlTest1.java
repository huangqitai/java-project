package com.qitai.xmlparse;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.List;

public class XmlTest1 {
    @Test
    public void test1() throws DocumentException, NoSuchFieldException, IllegalAccessException {
        //创建Jdom2的解析器对象
        SAXReader reader = new SAXReader();
        Document document = null;
        Element root = null;
        XsjgResponseVo responseVo = null;

        String responseResult = "<R><code>1</code><msg>报文重复上报。本手业务未关联至对应主产权。</msg><data></data></R>";
        //responseResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+responseResult;
        document = reader.read(new StringReader(responseResult));
        root = document.getRootElement();
        if (!root.getName().equals(XsjgResponseVo.class.getAnnotation(JacksonXmlRootElement.class).localName())) {
            System.out.println("xml内容无法转成  XsjgResponseVo 对象，请检查！");
        }
        responseVo = new XsjgResponseVo();
        List<Element> children = root.elements();
        for (Element child : children) {
            if (child.getText()==null||child.getTextTrim().isEmpty()){
                continue;
            }
            Field field = XsjgResponseVo.class.getDeclaredField(child.getName());
            field.setAccessible(true);
            if ("int".equals(field.getGenericType().getTypeName())) {
                field.set(responseVo, Integer.parseInt(child.getText()));
            } else {
                field.set(responseVo, child.getText());
            }
        }
        System.out.println(responseVo.getCode());
    }
}

