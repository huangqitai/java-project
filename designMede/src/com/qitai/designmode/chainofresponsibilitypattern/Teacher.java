package com.qitai.designmode.chainofresponsibilitypattern;

public class Teacher extends Leader {
    Leader leader;
    @Override
    void setNext(Leader leader) {
        this.leader = leader;
    }

    @Override
    Leader getNext() {
        return leader;
    }

    @Override
    void handleRequest(RequestDomain requestDomain) {
        if (requestDomain.getDay()<3){
            System.out.println(requestDomain.toString());
            System.out.println("处理结果：准假，    处理人：老师");
        }else if(getNext()!=null){
            getNext().handleRequest(requestDomain);
        }else {
            System.out.println("不予处理");
        }
    }
}
