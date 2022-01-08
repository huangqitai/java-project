package com.qitai.imageCode;

import net.sourceforge.tess4j.Tesseract;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageCode {
    /** tessdata 路径 */
    private static final String TESSDATA_PATH = "D:\\java-project\\github\\java-project\\proofScheme\\src\\main\\java\\com\\qitai\\imageCode\\tessdata";
    /** 验证码图片地址 */
    private static final String IMAGE_PATH = "D:\\java-project\\github\\java-project\\proofScheme\\src\\main\\java\\com\\qitai\\imageCode\\image(2).jpg";

    public static void main(String[] args) throws Exception {
        BufferedImage codeImage = ImageIO.read(new File(IMAGE_PATH));

        // 本地做个备份，好对比
        //write2disk(codeImage);

        Tesseract tessreact = new Tesseract();
        tessreact.setDatapath(TESSDATA_PATH);

        String result = tessreact.doOCR(codeImage);
        System.out.println("测验结果：[" + result.replace(" ", "").trim() + "]");
    }

    private static void write2disk(BufferedImage image) throws IOException {
        File file = new File("d:\\tesseract\\code.jpg");
        ImageIO.write(image, "jpg", file);
    }
}
