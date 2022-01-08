package com.qitai.utils;

import com.qitai.utils.exception.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author 胡现荣
 * @date 2020-07-20
 */
public class ZipTool {

    /**
     * @param filePaths        需要压缩的文件地址列表（绝对路径）
     * @param zipFilePath      需要压缩到哪个zip文件（无需创建这样一个zip，只需要指定一个全路径）
     * @param keepDirStructure 压缩后目录是否保持原目录结构
     */
    public static void compress(List<String> filePaths, String zipFilePath, Boolean keepDirStructure) throws IOException {
        byte[] buf = new byte[1024];
        File zipFile = new File(zipFilePath);
        //zip文件不存在，则创建文件，用于压缩
        if (!zipFile.exists()) {
            zipFile.createNewFile();
        }
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
            for (String relativePath : filePaths) {
                if (StringUtils.isEmpty(relativePath)) {
                    continue;
                }
                //绝对路径找到file
                File sourceFile = new File(relativePath);
                if (!sourceFile.exists()) {
                    continue;
                }

                FileInputStream fis = new FileInputStream(sourceFile);
                if (keepDirStructure != null && keepDirStructure) {
                    //保持目录结构
                    zos.putNextEntry(new ZipEntry(relativePath));
                } else {
                    //直接放到压缩包的根目录
                    zos.putNextEntry(new ZipEntry(sourceFile.getName()));
                }
                int len;
                while ((len = fis.read(buf)) > 0) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}