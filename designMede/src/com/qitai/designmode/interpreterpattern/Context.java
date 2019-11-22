package com.qitai.designmode.interpreterpattern;
import java.util.StringTokenizer;
/**
 * 上下文
 */
public class Context {

    private StringTokenizer tokenizer ;
    private String currentToken;
    public Context(String token){
        tokenizer=new StringTokenizer(token);
        nextToken();
    }
    public String nextToken() {
        if(tokenizer.hasMoreTokens()){
            currentToken=tokenizer.nextToken();
        }else{
            currentToken=null;
        }
        return currentToken;
    }
    public String getCurrentToken(){
        return currentToken;
    }
    public void skipToken(String token) throws ParseException{
        if(!token.equals(currentToken)){
            throw new ParseException("错误！！！"+"期待"+currentToken+"但是却得到"+token);
        }
        nextToken();
    }
    public int currentNumber() throws ParseException{
        int num=0;
        try{
            num=Integer.parseInt(currentToken);
        }catch(NumberFormatException e){
            throw new ParseException(e.toString());
        }
        return num;
    }

}

