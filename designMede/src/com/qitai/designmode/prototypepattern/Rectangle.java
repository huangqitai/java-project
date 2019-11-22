package com.qitai.designmode.prototypepattern;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Rectangle implements Serializable {
    private String id;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 深复制，利用序列化以二进制流的形式复制一个全新的对象，对象中引用对象也会新创建一个。
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Rectangle deepClone() throws IOException, ClassNotFoundException {
        //将对象写入流中 使用了装饰器模式
        ByteArrayOutputStream bao=new  ByteArrayOutputStream();
        ObjectOutputStream oos=new  ObjectOutputStream(bao);
        oos.writeObject(this);

        //将对象从流中取出
        ByteArrayInputStream bis=new  ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois=new ObjectInputStream(bis);
        return  (Rectangle) ois.readObject();
    }
}
