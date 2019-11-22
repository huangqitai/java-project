package com.qitai.designmode.templatepattern;

import org.junit.Test;

public class TestTemplate {
    @Test
    public void test(){
        Game contra = new Contra();
        contra.play();

        Game pvz = new Pvz();
        pvz.play();
    }
}
