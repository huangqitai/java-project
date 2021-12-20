package com.qitai.window;

public class BrowserTest {
    public static void main(String[] args) {
        try {
            String url = "https://zwfw.foshan.gov.cn/hoox-openAssess-ui/submitPage?sign=4f77ed74-3974-43c8-9b06-9bb89aad0101&p=11440606MB2D26310W4440712002040202104289pUa&u=%E5%B1%88%E8%8A%B8&s=%E5%AD%98%E9%87%8F%E6%88%BF%E8%BD%AC%E7%A7%BB%E7%99%BB%E8%AE%B0%28%E4%B9%B0%E5%8D%96%29";
            java.net.URI uri = java.net.URI.create(url);

            // 获取当前系统桌面扩展
            java.awt.Desktop dp = java.awt.Desktop.getDesktop();

            // 判断系统桌面是否支持要执行的功能
            if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                // File file = new File("D:\\aa.txt");
                // dp.edit(file);// 编辑文件
                dp.browse(uri);// 获取系统默认浏览器打开链接
                // dp.open(file);// 用默认方式打开文件
                // dp.print(file);// 用打印机打印文件
            }
        } catch (java.lang.NullPointerException e) {
            // 此为uri为空时抛出异常
            e.printStackTrace();
        } catch (java.io.IOException e) {
            // 此为无法获取系统默认浏览器
            e.printStackTrace();
        }
    }
}
