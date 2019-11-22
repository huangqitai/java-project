package com.qitai.designmode.observerpattern;

import org.junit.Test;

/**
 * 观察者模式是对象间一对多的依赖定义，当一个对象状态改变式，他的所有依赖对象都会收到更新。
 * java.util.Observer和java.util.Observable提供了内置的观察者模式
 * 被观察对象继承Observable，在改变状态时调用父类的setChange和notification方法就可以完成推送
 * 对于观察者注册和移除父类已经实现，观察者实现Observer接口，实现自己的update方法
 */
public class TestObserver {
    @Test
    public void testObserver(){
        Newspaper newspaper = new Newspaper("冬粉出版社","1999-08-16");

        Observer observerOne = new ObserverOne();
        Observer observerTwo = new ObserverTwo();
        Observer observerThree = new ObserverThree();

        NewsOffice newsOffice = new NewsOfficeOne();
        NewsOffice newsOfficeTwo = new NewsOfficeTwo();
        /**
         * 订阅者注册
         */
        newsOffice.registerObserver(observerOne);
        newsOffice.registerObserver(observerTwo);
        newsOffice.registerObserver(observerThree);
        /**
         * 报纸更新
         */
        newsOffice.setNewspaper(newspaper);

        newsOfficeTwo.registerObserver(observerOne);
        newsOfficeTwo.registerObserver(observerTwo);
        newsOfficeTwo.registerObserver(observerThree);

        Newspaper newspaperTwo = new Newspaper("无状态出版社","1999-08-16");
        newsOfficeTwo.setNewspaper(newspaperTwo);
    }
}
