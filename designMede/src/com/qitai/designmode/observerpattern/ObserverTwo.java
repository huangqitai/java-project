package com.qitai.designmode.observerpattern;

public class ObserverTwo implements Observer {
    @Override
    public void update(Newspaper newspaper) {
        System.out.println(newspaper.toString());
    }
}
