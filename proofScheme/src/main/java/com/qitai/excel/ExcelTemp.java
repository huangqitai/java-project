package com.qitai.excel;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelTemp {

    @Test
    public void export() throws Exception {
        //根据查询条件获取查询数据
        // 循环数据
        List<Object> list = new ArrayList<>();

        Map<String, Object> data = new HashMap<>();
        /**
         * 1	J1	2504585.59100000	38406221.69700000
         * 2	J2	2504585.29200000	38406270.50300000
         * 3	J3	2504548.43900000	38406273.31100000
         * 4	J4	2504548.46100000	38406221.19300000
         * 5	J1	2504585.59100000	38406221.69700000
         */
        data.put("xh","1");
        data.put("jzdh","J1");
        data.put("xzb","2504585.59100000");
        data.put("yzb","38406221.69700000");
        data.put("jzbc","48.1");
        //数据处理
        list.add(data);
        List<Object> list1 = new ArrayList<>();
        Map<String, Object> data1 = new HashMap<>();
        data1.put("bc","48.1");
        list1.add(data1);

        // 表格使用的数据
        Map<String,Object> map = new HashMap<>();
        map.put("jzds", list);
        //map.put("jzxs", list1);
        map.put("zl", "测试坐落");
        map.put("qllx", "测试权力类型");

        Date date = new Date();
        SimpleDateFormat simpl = new SimpleDateFormat("yyyyMMddHHmmss");
        String currntTime = simpl.format(date);
        //导出列表名
        String fileName = "导出数据" + "_" + currntTime;

        File dir = new File("D:\\臻善科技资源收藏\\工作文档20220321");
        if (!dir.exists()){
            dir.mkdirs();
        }
        //生成的导出文件
        File destFile = File.createTempFile(fileName, ".xlsx",dir);

        //transformer转到Excel
        //XLSTransformer transformer = new XLSTransformer();

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //InputStream resourceAsStream = this.getClass().getResourceAsStream("D:\\臻善科技资源收藏\\工作文档20220321\\440703013011JA00005.xlsx");
            InputStream resourceAsStream = new FileInputStream(new File("D:\\臻善科技资源收藏\\工作文档20220321\\440703013011JA00005.xlsx"));
            XLSTransformer xlsTransformer = new XLSTransformer();
            Workbook workbook = xlsTransformer.transformXLS(resourceAsStream, map);
            OutputStream os = new BufferedOutputStream(new FileOutputStream(destFile));
            workbook.write(os);
            resourceAsStream.close();
            os.flush();
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //使用完成后关闭流
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
