package com.qitai.designmode.facadepattern;

import org.junit.Test;

public class TestFacade {
    @Test
    public void test(){
        //外观类提供了但一接口，
        Facade facade = new Facade();

        facade.WatchTV();
    }
}
