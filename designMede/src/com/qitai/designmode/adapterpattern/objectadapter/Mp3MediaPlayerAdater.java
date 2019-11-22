package com.qitai.designmode.adapterpattern.objectadapter;

public class Mp3MediaPlayerAdater implements Mp3MediaPlayer {
    //使用组合让Mp3适配器拥有Mp4实现了类的行为
    Mp4MediaPlayer mp4MediaPlayer;
    Mp3MediaPlayerAdater(Mp4MediaPlayer mp4MediaPlayer){
        this.mp4MediaPlayer = mp4MediaPlayer;
    }
    @Override
    public void play(String fileType) {
        if ("mp3".equals(fileType)){
            System.out.println("我播放"+fileType);
        }
        else if ("mp4".equals(fileType)){
            mp4MediaPlayer.play(fileType);
        }
    }
}
