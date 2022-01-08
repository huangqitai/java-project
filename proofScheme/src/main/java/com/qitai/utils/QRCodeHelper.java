package com.qitai.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.qitai.utils.CheckUtil;
import com.qitai.utils.FileUtil;
import com.qitai.utils.exception.ExceptionUtil;
import com.qitai.utils.exception.ServiceException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class QRCodeHelper {

    private static final String FORMAT= "png";//png 或  jpeg
    private static final String CHARSET = "utf-8";
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    /**
     * 生成二维码，png格式
     * @param text 生成内容
     * @return base64编码图片
     */
    public static String toBase64QRCode(String text) {
        return getBase64Code(text, true);
    }

    /**
     * 生成条形码，png格式
     * @param text 生成内容
     * @return base64编码图片
     */
    public static String toBase64BarCode(String text) {
        return getBase64Code(text, false);
    }
    
    /**
     * 生成二维码
     * @param text 生成内容
     * @return 图片对象
     */
    public static BufferedImage toImageQRCode(String text) {
        return getImageCode(text, true);
    }

    /**
     * 生成条形码
     * @param text 生成内容
     * @return 图片对象
     */
    public static BufferedImage toImageBarCode(String text) {
        return getImageCode(text, false);
    }

    private static BufferedImage getImageCode(String text, boolean isToQR)
    {
      if(CheckUtil.isNullorEmpty(text)) {
        return null;
      }
    BufferedImage image=null;
    try {
        BitMatrix bitMatrix;
        // 内容所使用编码
        Hashtable<EncodeHintType,Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 0);
        if (isToQR) {
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, 300, 300, hints);
        } else {
            //判断字符串中是否包含中文字符（正则表达式判断中文）
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]|[\u0025]");
            StringBuilder buffer = new StringBuilder();
            for(int i = 0; i < text.length(); i++) {
                String chat = text.substring(i, i + 1);
                Matcher m = p.matcher(chat);
                if (m.find()) {
                    // 对中文字符进行url编码
                    chat = URLEncoder.encode(chat, CHARSET);
                }
                buffer.append(chat);
            }
            String newQrText = buffer.toString();
            if (newQrText.length() > 80) {
                throw new RuntimeException("转化条形码时，字段值太长，无法进行转化！");
            }

            List<String> result = new ArrayList<>();
            Pattern pattern = Pattern.compile("[^\\x00-\\xff]");
            Matcher matcher = pattern.matcher(newQrText);
            while (matcher.find()){
                //匹配双字节字符
                result.add(matcher.group());
            }
            if(result.size() > 0){
                throw new RuntimeException("条形码内容存在非法字符:" + String.join(",", result));
            }

            bitMatrix = new MultiFormatWriter().encode(newQrText, BarcodeFormat.CODE_128, 200, 200, hints);
        }

        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
            }
        }
    }catch(Exception ex){

    	throw new ServiceException(ex);
    }
    return image;
  }
    
    private static String getBase64Code(String text, boolean isToQR)
    {
      BufferedImage image=getImageCode(text, isToQR);
      if(image==null) {
         return "";
      }

        String qrString = "";
        ByteArrayOutputStream outStream = null;
        try {
            outStream = new ByteArrayOutputStream();
            if (!ImageIO.write(image, FORMAT, outStream)) {
                throw new Exception("无法生成" + FORMAT + "格式的图片");
            }

            qrString = Base64.getEncoder().encodeToString(outStream.toByteArray());
            qrString = "data:image/"+FORMAT+";base64," + qrString;
        }catch(Exception ex){

        	throw new ServiceException(ex);
        } finally {
        	FileUtil.tryCloseStream(outStream);
        }
        return qrString;
    }
}
