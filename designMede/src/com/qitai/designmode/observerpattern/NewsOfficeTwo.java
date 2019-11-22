package com.qitai.designmode.observerpattern;

public class NewsOfficeTwo implements NewsOffice {
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
