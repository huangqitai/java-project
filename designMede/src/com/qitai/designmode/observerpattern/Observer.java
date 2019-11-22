package com.qitai.designmode.observerpattern;

/**
 * 观察者接口，所有的观察者必须实现，主题在通知观察者时的统一接口
 */
public interface Observer {
    void update(Newspaper newspaper);
}
