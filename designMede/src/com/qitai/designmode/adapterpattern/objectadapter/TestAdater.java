package com.qitai.designmode.adapterpattern.objectadapter;

import org.junit.Test;

public class TestAdater {
    @Test
    public void test(){
        Mp4MediaPlayer mp4MediaPlayer = new Mp4MediaPlayerImpl();
        Mp3MediaPlayer mp3MediaPlayer = new Mp3MediaPlayerAdater(mp4MediaPlayer);
        mp3MediaPlayer.play("mp3");
        mp3MediaPlayer.play("mp4");
    }
}
