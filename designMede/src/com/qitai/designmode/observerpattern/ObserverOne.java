package com.qitai.designmode.observerpattern;

public class ObserverOne implements Observer{
    @Override
    public void update(Newspaper newspaper) {
        System.out.println(newspaper.toString());
    }
}
