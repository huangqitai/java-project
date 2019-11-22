package com.qitai.designmode.proxypattern;

/**
 * 车票代售
 */
public class StationTicketProxy implements TicketOffice{
    StationTicket stationTicket;

    @Override
    public void ticket() {
        //作为代理，进行一定程度上的控制
        if (stationTicket==null){
            stationTicket = new StationTicket();
        }
        stationTicket.ticket();
    }
}
