package com.qitai.designmode.proxypattern;

import org.junit.Test;

public class TestProxy {
    @Test
    public void test(){
        TicketOffice ticketOffice = new StationTicketProxy();
        ticketOffice.ticket();
        ticketOffice.ticket();
    }
}
