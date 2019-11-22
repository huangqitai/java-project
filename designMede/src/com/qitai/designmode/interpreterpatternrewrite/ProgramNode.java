package com.qitai.designmode.interpreterpatternrewrite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramNode extends Node {

    @Override
    public void parse(Context context) throws ParseException {

        Pattern pattern = Pattern.compile("<[a-zA-Z]{1,}>");
        Matcher matcher = pattern.matcher(context.getCurrentToken());
        if (matcher.find()) {
            String tag = matcher.group(0).substring(matcher.group(0).indexOf("<")+1,matcher.group(0).indexOf(">"));
            System.out.println("标签"+tag+"的内容:"+context.getCurrentToken().substring(context.getCurrentToken().indexOf(">")+1,context.getCurrentToken().lastIndexOf("<")));
        }
    }
    public String toString(){
        return "标签：";
    }

}
