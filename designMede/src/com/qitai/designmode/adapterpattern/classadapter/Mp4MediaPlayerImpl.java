package com.qitai.designmode.adapterpattern.classadapter;

public class Mp4MediaPlayerImpl implements Mp4MediaPlayer {
    @Override
    public void play(String fileType) {
        System.out.println("我播放"+fileType);
    }
}
