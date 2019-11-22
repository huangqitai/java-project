package com.qitai.designmode.adapterpattern.classadapter;

import org.junit.Test;

public class TestAdater {
    @Test
    public void test(){
        Mp3MediaPlayer mp3MediaPlayer = new Mp3MediaPlayerAdater();
        mp3MediaPlayer.play("mp3");
        mp3MediaPlayer.play("mp4");
    }
}
