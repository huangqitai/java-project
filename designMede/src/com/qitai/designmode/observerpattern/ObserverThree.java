package com.qitai.designmode.observerpattern;

public class ObserverThree implements Observer {
    @Override
    public void update(Newspaper newspaper) {
        System.out.println(newspaper.toString());
    }
}
