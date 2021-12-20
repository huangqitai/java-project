package com.qitai.window;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.jna.platform.win32.WinDef;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;


public class WindowsTest extends Application {
    Button btn;

    @Override
    public void start(Stage primaryStage) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] gs = ge.getScreenDevices();
            GraphicsDevice gd = gs[0];
            Robot robot = new Robot(gd);
            int i = gs.length;
            btn = new Button("" + i);

            StackPane root = new StackPane();
            root.getChildren().add(btn);

            Scene scene = new Scene(root, 1000, 1000);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showOnScreen2(int screen, JFrame frame) {
        Window[] windows = Window.getOwnerlessWindows();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        if (screen > -1 && screen < gd.length) {
            frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, frame.getY());
        } else if (gd.length > 0) {
            frame.setLocation(gd[1].getDefaultConfiguration().getBounds().x, frame.getY());
        } else {
            throw new RuntimeException("No Screens Found");
        }
    }

    @Test
    public void getWindows() {
        System.out.println("aaa");
    }

    public static void main(String[] args) throws Exception {
        WinDef.RECT qqwin_rect = new WinDef.RECT();
        //launch(args);
        String path = getQRcodeService("https://zwfw.foshan.gov.cn/hoox-openAssess-ui/submitPage?sign=4f77ed74-3974-43c8-9b06-9bb89aad0101&p=11440606MB2D26310W4440712002040202104289pUa&u=%E5%B1%88%E8%8A%B8&s=%E5%AD%98%E9%87%8F%E6%88%BF%E8%BD%AC%E7%A7%BB%E7%99%BB%E8%AE%B0%28%E4%B9%B0%E5%8D%96%29");
        JFrame jf2 = new JFrame();
        //添加图像
        jf2.add(new JLabel(new ImageIcon(path)));
        jf2.setSize(1000, 1000);
        jf2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf2.setVisible(true);
        showOnScreen2(1, jf2);
    }

    /**
     * 在暂存区生成二维码
     *
     * @param url 二维码地址
     * @return 文件路径
     */
    public static String getQRcodeService(String url) throws Exception {
        int width = 300;//二维码图片的宽度
        int height = 300;//二维码图片的高度
        String format = "png";//二维码格式
        String content = url;//二维码内容
        //定义二维码内容参数
        HashMap hints = new HashMap();
        //设置字符集编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //设置容错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //设置边框距
        hints.put(EncodeHintType.MARGIN, 2);
        //指定二维码内容
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

       /* // 获取暂存区地址
        SysConfigure cfg = sysConfService.getConfigureByKey(SystemConfigureNames.CATALOG_SERVICE, "%%stackfiles%");
        String templatePath;
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        if (cfg == null || cfg.getValue().isEmpty()) {
            throw new RuntimeException("请配置暂存区路径");
        } else {
            templatePath = cfg.getValue() + "/暂存二维码";
        }*/
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String templatePath = "D:\\opt\\stackfiles\\ORcode";
        File fileDir = new File(templatePath);
        if (!fileDir.exists()) {//如果文件夹不存在
            fileDir.mkdir();//创建文件夹
        }

        File file = new File(templatePath + "/" + timestamp + ".png");

        //指定生成图片的保存路径
        Path path = file.toPath();
        //生成二维码
        MatrixToImageWriter.writeToPath(bitMatrix, format, path);
        return path.toString();
    }
}