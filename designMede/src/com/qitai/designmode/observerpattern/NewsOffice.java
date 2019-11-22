package com.qitai.designmode.observerpattern;

/**
 * 以报社---订阅者为应用场景，设计观察者模式
 * 有多间报社，每间报社面向许多订阅者，只要有新的报纸出版就将报纸推送给订阅了报纸的人(订阅者)
 * 在此过程中，报社作为主题，订阅者就是观察者
 */

import java.util.HashSet;
import java.util.Set;

/**
 * 报社接口，
 */
public interface NewsOffice {
    Set<Observer> observers = new HashSet<>();
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void setNewspaper(Newspaper newspaper);
    void notification(Newspaper newspaper);
}
