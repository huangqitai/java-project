package com.qitai.designmode.adapterpattern.classadapter;

/**
 * 利用继承，实现MP4的方法
 */
public class Mp3MediaPlayerAdater extends Mp4MediaPlayerImpl implements Mp3MediaPlayer {
    @Override
    public void play(String fileType) {
        if ("mp4".equals(fileType)){
            super.play(fileType);
        }else if ("mp3".equals(fileType)){
            System.out.println("我播放"+fileType);
        }
    }
}
