package com.qitai.designmode.proxypattern;

/**
 * 车站售票处
 */
public class StationTicket implements TicketOffice {
    int ticketCount = 1000;
    StationTicket(){
        init();
    }
    void init(){
        System.out.println("连接中..");
    }

    @Override
    public void ticket() {
        if (ticketCount>0){
            System.out.println("正在出票中");
            ticketCount--;
        }else System.out.println("无票");
    }
}
