package com.qitai.designmode.facadepattern;

/**
 * 外观类，提供简单接口，委托子系统接口
 */
public class Facade {
    Power power;
    Display display;
    Acceptor acceptor;
    Audio audio;
    Facade(){
        power = new Power();
        display = new Display();
        acceptor = new Acceptor();
        audio = new Audio();
    }
    //单一接口，委托子接口
    public void WatchTV(){
        power.onPower();
        display.onDisplay();
        acceptor.onAcceptor();
        audio.onAudio();
    }
}
