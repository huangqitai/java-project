package com.qitai.video;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.junit.Test;


import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import javax.imageio.ImageIO;

import static org.bytedeco.javacpp.avformat.av_register_all;

public class subVideoToImage {


    @Test
    public void test7(){
        String userHome = System.getProperties().getProperty("user.home");

        String userDir = System.getProperties().getProperty("user.dir");
        String osName = System.getProperties().getProperty("os.name");
        System.out.println(userHome);
        System.out.println(userDir);
        System.out.println(osName);
    }
    @Test
    public void test6() throws IOException {
        File file = new File("/opt/xxx");
        file.mkdirs();
        File file1 = new File("D:/opt/xxx/test.png");
        file1.createNewFile();
    }
    @Test
    public void getQRcodeService(){
        int width = 300;//二维码图片的宽度
        int height = 300;//二维码图片的高度
        String format = "png";//二维码格式
        String content = "www.baidu.com";//二维码内容
        //定义二维码内容参数
        HashMap hints = new HashMap();
        //设置字符集编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //设置容错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //设置边框距
        hints.put(EncodeHintType.MARGIN, 2);
        String templatePath = "/opt/stackfiles/videoToImage";
        //生成二维码
        try {
            //指定二维码内容
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,hints);

            File fileDir = new File(templatePath);
            if(!fileDir.exists()){//如果文件夹不存在
                fileDir.mkdir();//创建文件夹
            }

            File file = new File(templatePath+"/权利人身份证明.png");

            //指定生成图片的保存路径
            Path path = file.toPath();
            //生成二维码
            MatrixToImageWriter.writeToPath(bitMatrix, format, path);
            System.out.println( path.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void createImage() throws IOException {
        int width = 400;
        int height = 300;
        // 创建BufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取Graphics2D
        Graphics2D g2d = image.createGraphics();
        // 画图
        g2d.setColor(new Color(255, 0, 0));
        g2d.setStroke(new BasicStroke(1));
        g2d.drawImage(image, null, width, height);
        //释放对象
        g2d.dispose();
        // 保存文件
        ImageIO.write(image, "png", new File("/opt/stackfiles/videoToImage/权利人身份证明.png"));
    }

    @Test
    public void test8() throws FrameGrabber.Exception {
        FFmpegFrameGrabber g = new FFmpegFrameGrabber("textures/video/anim.mp4");
        g.start();


        g.stop();
    }
    @Test
    public void test() {
        File fileDir = new File("D:/opt/jobfiles");
        if(!fileDir.exists()){//如果文件夹不存在
            fileDir.mkdir();//创建文件夹
        }
        String videoPath = "D:\\opt\\jobfiles\\preview\\202004\\44090020042200022\\2d208d68-49bb-4757-97b1-5175a887a3b5.mp4";
        String imagePath = "D:/test.png";
        FFmpegFrameGrabber ff = null;
        try {
            ff = FFmpegFrameGrabber.createDefault(videoPath);
            ff.start();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }

        int ffLength = ff.getLengthInFrames();
        Frame f;
        String pngPath = "";
        String rotate_old=ff.getVideoMetadata("rotate");//视频旋转角度，可能是null
        int i = 0;
        while (i < ffLength) {
            try {
                f = ff.grabImage();
                //截取第6帧
                if ((i > 0) && (f.image != null)) {
                    //截图并放入指定位置
                    Java2DFrameConverter converter = new Java2DFrameConverter();
                    BufferedImage bi = converter.getBufferedImage(f);
                    pngPath = imagePath;
                    File output = new File(pngPath);
                    ImageIO.write(bi, "png", output);
                    break;
                }
                i++;
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            ff.stop();
            if(rotate_old!=null && !rotate_old.isEmpty()){
                int rotate=Integer.parseInt(rotate_old);
                rotatePhonePhoto(imagePath,rotate);
            }
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }

    public  String rotatePhonePhoto(String fullPath, int angel) {
        String image_type="png";
        BufferedImage src;
        try {
            src = ImageIO.read(new File(fullPath));
            int src_width = src.getWidth(null);
            int src_height = src.getHeight(null);

            int swidth = src_width;
            int sheight = src_height;

            if (angel == 90 || angel == 270) {
                swidth = src_height;
                sheight = src_width;
            }
            Rectangle rect_des = new Rectangle(new Dimension(swidth, sheight));
            BufferedImage res = new BufferedImage(rect_des.width, rect_des.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = res.createGraphics();
            g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
            g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);
            g2.drawImage(src, null, null);
            ImageIO.write(res,image_type, new File(fullPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullPath;

    }

    private void videoFetch(String videoFilePath,String imagePath) throws IOException, FrameGrabber.Exception {
        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(videoFilePath);
        ff.start();
        int ffLength = ff.getLengthInFrames();
        Frame f;
        int i = 0;
        while (i < ffLength) {
            f = ff.grabImage();
            //截取第6帧
            if ((i > 5) && (f.image != null)) {
                //截图并放入指定位置
                Java2DFrameConverter converter = new Java2DFrameConverter();
                BufferedImage bi = converter.getBufferedImage(f);
                File output = new File(imagePath);
                ImageIO.write(bi, "png", output);
                break;
            }
            i++;
            ff.stop();
        }
    }
}