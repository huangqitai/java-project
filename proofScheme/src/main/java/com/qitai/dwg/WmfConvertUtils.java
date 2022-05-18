package com.qitai.dwg;

import com.aspose.cad.*;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.aspose.cad.imageoptions.JpegOptions;
import com.aspose.cad.imageoptions.SvgOptions;
import net.arnx.wmf2svg.gdi.svg.SvgGdi;
import net.arnx.wmf2svg.gdi.wmf.WmfParser;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.wmf.tosvg.WMFTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.zip.GZIPOutputStream;


/**
 * @ClassName WmfConvertUtils
 * @Description 用于将Wmf转为jpg
 * @Author chenwl
 * @DATE 2020/9/14 14:51
 * @Version 1.0
 **/
public class WmfConvertUtils {
    public static final Logger log= LoggerFactory.getLogger(WmfConvertUtils.class);

    public static void main(String[] args) throws Exception {
//        String result = convert("C:\\Users\\Administrator\\Desktop\\MfQ\\b\\b.wmf");
//        String wmfPath = "C:\\Users\\Administrator\\Desktop\\MfQ\\b\\b.wmf";
//        String jpgPath = convert(wmfPath);
//        FileUtils.deleteFile(wmfPath);
//
//        byte[] file = FileUtils.readFileToByteArray(new File(jpgPath));
//        //图片添加水印
//        file = WatermarkUtil.addWaterMarkForImage("hah","仅供查询","",file,-30);
//        //写入本地文件
//        FileUtils.write(jpgPath,file,true,true);
//        String mapFile =  Base64Utils.toBase64CodeString(file);
//
//        System.out.println(mapFile);
//        dwgToSvg("src","path");
        String path="C:/Users/Administrator/Desktop/扫描看图文档/1幢/分户图矢量/101.dwg";
        dwgTOjpg(path);

    }

    public static String convert2(String path) throws TranscoderException,
            IOException {
        String wmfPath = path;
        File wmf = new File(wmfPath);
        FileInputStream wmfStream = new FileInputStream(wmf);
        ByteArrayOutputStream imageOut = new ByteArrayOutputStream();
        int noOfByteRead = 0;
        while ((noOfByteRead = wmfStream.read()) != -1) {
            imageOut.write(noOfByteRead);
        }
        imageOut.flush();
        wmfStream.close();
        // wmf 转换为svg
        WMFTranscoder transcoder = new WMFTranscoder();
        // TranscodingHints hints = new TranscodingHints();
        // hints.put(WMFTranscoder.KEY_HEIGHT, 1000f);
        // hints.put(WMFTranscoder.KEY_WIDTH, 1500f);
        // transcoder.setTranscodingHints(hints);
        TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(
                imageOut.toByteArray()));
        ByteArrayOutputStream svg = new ByteArrayOutputStream();
        TranscoderOutput output = new TranscoderOutput(svg);
        transcoder.transcode(input, output);
        String svgFile = replace(wmfPath, "wmf", "svg");
        FileOutputStream fileOut = new FileOutputStream(svgFile);
        fileOut.write(svg.toByteArray());
        fileOut.flush();
        fileOut.close();
        svg.close();
        // svg -> jpg
        ImageTranscoder it = new JPEGTranscoder();
        it.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(1f));
        it.addTranscodingHint(ImageTranscoder.KEY_WIDTH, new Float(1000));
        ByteArrayOutputStream jpg = new ByteArrayOutputStream();
        it.transcode(new TranscoderInput(new ByteArrayInputStream(svg
                .toByteArray())), new TranscoderOutput(jpg));
        String jpgFile = replace(wmfPath, "wmf", "jpg");
        FileOutputStream jpgOut = new FileOutputStream(jpgFile);
        jpgOut.write(jpg.toByteArray());
        jpgOut.flush();
        jpgOut.close();
        jpg.close();
        // Filor.deleteFile(svgFile);// 删除掉中间文件
        return jpgFile;
    }

    public static String convert(String path) {
        try {
            String svgFile = replace(path, "wmf", "svg");
            wmfToSvg(path, svgFile);
            String jpgFile = replace(path, "wmf", "jpg");
            svgToJpg(svgFile, jpgFile);
            deleteFile(svgFile);
            return jpgFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private static void deleteFile(String svgFile) {
        File file = new File(svgFile);
        file.delete();
    }

    /**
     * dwg转svg svg转jpg
     * @param path
     * @return
     */
    public static String wenlongconvert(String path) {
        try {
            String svgFile=replace(path,"dwg","svg");
            dwgToSvg(path,svgFile);
            String jpgFile = replace(path, "dwg", "jpg");
            svgToJpg(svgFile, jpgFile);
            deleteFile(svgFile);
            return jpgFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * dwg转jpg
     * @param path
     * @return
     */
    public static String dwgTOjpg(String path) {
        try {
            String jpgFile=replace(path,"dwg","jpg");
            dwgToJpgVersion(path,jpgFile);
            log.info(jpgFile);
            return jpgFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 将svg转化为JPG
     *
     * @param src
     * @param dest
     */
    public static String  svgToJpg(String src, String dest) {
        FileOutputStream jpgOut = null;
        FileInputStream svgStream = null;
        ByteArrayOutputStream svgOut = null;
        ByteArrayInputStream svgInputStream = null;
        ByteArrayOutputStream jpg = null;
        try {
            // 获取到svg文件
            File svg = new File(src);
            svgStream = new FileInputStream(svg);
            svgOut = new ByteArrayOutputStream();
            // 获取到svg的stream
            int noOfByteRead = 0;
            while ((noOfByteRead = svgStream.read()) != -1) {
                svgOut.write(noOfByteRead);
            }
            JPEGTranscoder it = new JPEGTranscoder();
            it.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(1f));
            it.addTranscodingHint(ImageTranscoder.KEY_WIDTH, new Float(1000));
            jpg = new ByteArrayOutputStream();
            svgInputStream = new ByteArrayInputStream(svgOut.toByteArray());
            it.transcode(new TranscoderInput(svgInputStream),
                    new TranscoderOutput(jpg));
            jpgOut = new FileOutputStream(dest);
            jpgOut.write(jpg.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (jpgOut != null) {
                    jpgOut.flush();
                    jpgOut.close();
                }
                if (jpg != null) {
                    jpg.close();
                }
                if (svgInputStream != null) {
                    svgInputStream.close();
                }
                if (svgOut != null) {
                    svgOut.close();
                }
                if (svgStream != null) {
                    svgStream.close();
                }
                if (svgOut != null) {
                    svgOut.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dest;
    }

    /**
     * DWG装换成jpg
     * @param src
     * @param dest
     * @throws Exception
     */
    public static void dwgToJpgVersion(String src, String dest){
        Image objImage = Image.load(src);
        CadRasterizationOptions cadRasterizationOptions = new CadRasterizationOptions();
        cadRasterizationOptions.setPageWidth(800);
        cadRasterizationOptions.setPageHeight(600);
        ImageOptionsBase options;
        options = new JpegOptions();
        options.setVectorRasterizationOptions(cadRasterizationOptions);
        objImage.save(dest, options);

    }

    /**
     * dwg转svg svg转jpg
     * @param path
     * @return
     */
    public static String dwgconvert(String path) {
        try {
            String svgFile=replace(path,"dwg","svg");
            dwgToSvg(path,svgFile);
            String jpgFile = replace(path, "dwg", "jpg");
            svgToJpg(svgFile, jpgFile);
            //deleteFile(svgFile);
            //deleteFile(path);
            return jpgFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * 将wmf转换为svg
     *
     * @param src
     * @param dest
     */
    public static void wmfToSvg(String src, String dest) {
        boolean compatible = false;
        try {
            InputStream in = new FileInputStream(src);
            WmfParser parser = new WmfParser();
            final SvgGdi gdi = new SvgGdi(compatible);
            parser.parse(in, gdi);

            Document doc = gdi.getDocument();
            OutputStream out = new FileOutputStream(dest);
            if (dest.endsWith(".svgz")) {
                out = new GZIPOutputStream(out);
            }

            output(doc, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 将dwg转换为svg
     *
     * @param src
     * @param dest
     */
    public static void dwgToSvg(String src, String dest){
        log.info("进入dwg转svg方法");
        com.aspose.cad.Image cadImage = com.aspose.cad.Image.load(src);
        CadRasterizationOptions rasterizationOptions = new CadRasterizationOptions();
        rasterizationOptions.setPageWidth(16000);
        rasterizationOptions.setPageHeight(16000);
        rasterizationOptions.setAutomaticLayoutsScaling(true);
        rasterizationOptions.setNoScaling (false);
        rasterizationOptions.setContentAsBitmap(true);
        rasterizationOptions.setLayouts(new String[] {"Model"});
        rasterizationOptions.setDrawType(1);

//        CadRasterizationOptions rasterizationOptions = new CadRasterizationOptions();
//        rasterizationOptions.setLayouts(new String[]{"Model"});
//        rasterizationOptions.setNoScaling(true);
//        rasterizationOptions.setBackgroundColor(Color.getWhite());
//        float width=cadImage.getWidth();
//        float height=cadImage.getHeight();
//        rasterizationOptions.setPageWidth(width);
//        rasterizationOptions.setPageHeight(height);
//        rasterizationOptions.setAutomaticLayoutsScaling(true);
//        rasterizationOptions.setDrawType(CadDrawTypeMode.UseObjectColor);


        SvgOptions svgOptions = new SvgOptions();
        svgOptions.setVectorRasterizationOptions(rasterizationOptions);

        // Set Graphics options
        rasterizationOptions.getGraphicsOptions().setSmoothingMode(SmoothingMode.HighQuality);
        rasterizationOptions.getGraphicsOptions().setTextRenderingHint(TextRenderingHint.AntiAliasGridFit);
        rasterizationOptions.getGraphicsOptions().setInterpolationMode(InterpolationMode.HighQualityBicubic);

        // Export to PDF by calling the Save method
        cadImage.save(dest, svgOptions);
    }

    private static void output(Document doc, OutputStream out) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,
                "-//W3C//DTD SVG 1.0//EN");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
                "http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd");
        transformer.transform(new DOMSource(doc), new StreamResult(out));
        out.flush();
        out.close();
    }

    public static String replace(String original ,String find,String replace)
    {
        if (original==null || find==null ||replace==null)
        {
            return original;
        }
        int findLen = find.length();
        int originalLen = original.length();
        if (originalLen==0 || findLen==0)
        {
            return original;
        }
        StringBuffer sb = new StringBuffer();
        int begin = 0; //下次检索开始的位置
        int i = original.indexOf(find); //找到的子串位置
        while (i!=-1)
        {
            sb.append(original.substring(begin,i));
            sb.append(replace);
            begin = i + findLen;
            i = original.indexOf(find,begin);
        }
        if (begin<originalLen)
        {
            sb.append(original.substring(begin));
        }
        return sb.toString();
    }


}
