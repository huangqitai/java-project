package com.qitai.designmode.observerpattern;

/**
 * 注册、删除观察者
 * 报社的实现类，拥有报纸实例，当报纸更新(也就是setNewspaper被调用时)，会将报纸推送给所有注册了的观察者
 * notification()方法；
 */
public class NewsOfficeOne implements NewsOffice {
    Newspaper newspaper;
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void setNewspaper(Newspaper newspaper) {
        this.newspaper = newspaper;
        notification(newspaper);
    }

    @Override
    public void notification(Newspaper newspaper) {
        for (Observer observer:observers) {
            observer.update(newspaper);
        }
    }
}
