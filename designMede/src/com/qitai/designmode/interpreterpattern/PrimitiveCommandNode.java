package com.qitai.designmode.interpreterpattern;

public class PrimitiveCommandNode extends Node {

    String name;
    public void parse(Context context) throws ParseException {
        name=context.getCurrentToken();
        context.skipToken(name);
        if(!name.equals("go") && !name.equals("left") && !name.equals("right") ){
            throw new ParseException("错误！！！非法字符："+name);
        }
    }

    public String toString(){
        return name;
    }
}
