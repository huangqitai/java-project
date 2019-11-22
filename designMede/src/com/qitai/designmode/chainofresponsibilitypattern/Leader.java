package com.qitai.designmode.chainofresponsibilitypattern;

/**
 * 请求处理抽象类
 */
public abstract class Leader {
    abstract void setNext(Leader leader);
    abstract Leader getNext();
    abstract void handleRequest(RequestDomain requestDomain);
}
