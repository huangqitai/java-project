package com.qitai.designmode.interpreterpatternrewrite;

public class ParseException extends Exception {
    private final static long serialVersionUID = 3996163326179443976L;

    public ParseException(String message){
        super(message);
    }
}
