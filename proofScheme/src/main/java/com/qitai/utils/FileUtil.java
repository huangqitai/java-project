package com.qitai.utils;

import com.qitai.utils.CheckUtil;
import com.qitai.utils.exception.ExceptionUtil;
import com.qitai.utils.exception.FileNotAcceptException;
import com.qitai.utils.exception.ServiceException;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DecimalFormat;

public final class FileUtil {
	/**
	 * 协议名与服务器地址的分隔符(://)最大可能位置，查找://的位置如果超过此常量值，
	 * 则表示是查询参数中的内容，不算是URL地址中的协议名分隔符。
	 */
	public static final int SCHEME_SPLIT_POS=8;
	/**
	 * 默认扩展的白名单
	 */
	private static final String[] DEFAULT_BLACK_FILE_EXT =new String[] {".jpg", ".jpeg", ".png", ".txt", ".pdf", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx",".mp4"};

	/**
	 * 获取文件名的扩展名部分，转换为小写字母，包含点(.)。
	 * 如传入文件名：“test.jpg”，返回“.jpg”。
	 * @param sFileName
	 * @return
	 */
	public static String getExtend(String sFileName){
		if(CheckUtil.isNullorEmpty(sFileName)) {
            return "";
        }
		int iPos=sFileName.lastIndexOf('.');
		if(iPos<0) {
            return "";
        }
		return sFileName.substring(iPos).toLowerCase();
	}

	/**
	 * 获取文件名的扩展名部分，转换为小写字母，不包含点(.)。
	 * 如传入文件名：“test.jpg”，返回“jpg”。
	 * @param sFileName
	 * @return
	 */
	public static String getExtendNot(String sFileName){
		if(CheckUtil.isNullorEmpty(sFileName)) {
			return "";
		}
		int iPos=sFileName.lastIndexOf('.');
		if(iPos<0) {
			return "";
		}
		return sFileName.substring(iPos+1).toLowerCase();
	}
	/**
	 * 获取文件名排除扩展名部分，如果有路径部分，保留路径。
	 * 如传入文件名：“test.jpg”，返回“test”；“%%jobf%/2019/test.jpg”返回“%%jobf%/2019/test”。
	 * @param sFileName
	 * @return
	 */
	public static String getFileName(String sFileName)
	{
		if(CheckUtil.isNullorEmpty(sFileName)) {
            return "";
        }
		int iPos=sFileName.lastIndexOf('.');
		if(iPos<0) {
            return sFileName;
        }
		return sFileName.substring(0,iPos);
	}
	/**
	 * 获取文件名的最后一部分的名称，即不包含路径(支持/或\分隔）。
	 * 如传入文件名：“test.jpg”，返回“test”；“%%jobf%/2019/test.jpg”返回“test”。
	 * @param sFileName
	 * @return
	 */
	public static String getOnlyFileName(String sFileName)
	{
		if(CheckUtil.isNullorEmpty(sFileName)) {
            return "";
        }
		int iPos=sFileName.lastIndexOf('.');
		if(iPos>=0) {
            sFileName=sFileName.substring(0,iPos);
        }
		iPos=sFileName.lastIndexOf('/');
		int temp=sFileName.lastIndexOf('\\');
		if(temp>iPos) {
            iPos=temp;
        }

		if(iPos<0) {
            return sFileName;
        }
		return sFileName.substring(iPos+1);
	}

	/**
	 * 判断扩展名是否为图片类型的扩展名(.png,.jpg,.jpeg,.gif)
	 * @param ext 要判断的扩展名
	 * @return
	 */
	public static boolean isImageExtName(String ext)
	{
		if(!CheckUtil.isNullorEmpty(ext) && ext.length()<8){
			int iPos=0;
			if(ext.charAt(iPos)=='.') {
                iPos++;
            }
			char chX=ext.charAt(iPos++);
			String sNext=ext.substring(iPos);
			if(chX=='j' || chX=='J'){//jpg jpeg
				return "pg".equalsIgnoreCase(sNext) || "peg".equalsIgnoreCase(sNext);
			}else if(chX=='p' || chX=='P'){//png
				return "ng".equalsIgnoreCase(sNext);
			}else if(chX=='g' || chX=='G'){//gif
				return "if".equalsIgnoreCase(sNext);
			}else if(chX=='b' || chX=='B') {//bmp
				return "mp".equalsIgnoreCase(sNext);
			}
		}
		return false;
	}

	/**
	 * 创建目录
	 * LXY
	 * @param destDirName
	 * @return
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {// 判断目录是否存在
			return true;
		}
		return dir.mkdirs();
	}

	/**
	 * 基于扩展名获得mime类型串（如：image/jpeg）
	 * @param sExt
	 * @return mime类型串，如果sExt为空，返回“application/octet-stream”；如果无法识别，返回application/扩展名；
	 */
	public static String GetMimeFromExt(String sExt)
	{
		String sMime = "application/octet-stream";
		if(CheckUtil.isNullorEmpty(sExt)){
			return sMime;
		}

		sExt = sExt.toLowerCase();
		if(sExt.charAt(0)=='.') {
            sExt=sExt.substring(1);
        }

		switch(sExt)
		{
		case "jpg":
		case "jpe":
			sMime = "image/jpeg";
			break;
		case "png":
		case "gif":
			sMime="image/"+sExt;
			break;
		case "txt":
			sMime = "text/plain;charset=utf-8";
			break;
		case "mp3":
			sMime="audio/mpeg";
			break;
		case "ogg":
		case "wav":
			sMime="audio/"+sExt;
			break;
		case "mp4":
		case "webm":
			sMime="video/"+sExt;
			break;
		case "ogv":
			sMime="video/ogg";
			break;
		case "doc":
		case "docx":
			sMime="application/msword";
			break;
		case "xls":
		case "xlsx":
			sMime="application/msexcel";
			break;
		case "ppt":
		case "pptx":
			sMime="application/mspowerpoint";
			break;
		default://pdf、rtf
			if(!sExt.isEmpty()) {
                sMime="application/"+sExt;
            }
			break;
		}
		return sMime;
	}

  /**
   * 判断地址是否为网络地址。以ftp://、http://或https://开头则返回非0值
   * @param path
   * @return path为空返回-1。
   * 以http://开头返回1，以https://开头返回2。
   * 以ftp:// 开头返回10。
   * 其它值返回0
   */
  public static int IsNetPath(String path)
  {
  	if(CheckUtil.isNullorEmpty(path)) {
        return -1;
    }
  	int iPos=path.indexOf("://");
  	if(iPos<0 || iPos> com.qitai.utils.FileUtil.SCHEME_SPLIT_POS) {
        return 0;
    }

  	String sPrefix=path.substring(0,iPos).toLowerCase();
  	if(sPrefix.equals("ftp")) return 10;
  	if(sPrefix.equals("http")) return 1;
  	if(sPrefix.equals("https")) return 2;
  	if(sPrefix.equals("mongodb")) return 20;

  	return 0;
  }

  /**
   * 查找URL地址的路径开始位置。如果为null或Empty，返回-1；如果不包含服务器地址，返回0；
   * 否则返回除服务器地址后的第一个路径位置(/)，如果仅服务器地址且无查询参数(?)，返回字符串长度，如果有?则返回?位置。<br/>
   * 比如以下情况都返回23（即服务器地址后的第一个字符位置）：<br/>
   * http://192.168.1.1:8080/mainWeb/work<br/>
   * http://192.168.1.1:8080<br/>
   * http://192.168.1.1:8080?name=test
   * @param url
   * @return
   */
  public static int indexOfPath(String url)
  {
  	if(CheckUtil.isNullorEmpty(url)) {
        return -1;
    }
  	int iPos=url.indexOf("://");
  	if(iPos<0 || iPos> com.qitai.utils.FileUtil.SCHEME_SPLIT_POS) {
        return 0;
    }
  	int iquery=url.indexOf('?');
  	int inx=url.indexOf("/",iPos+3);
  	if(inx<0) {
        inx=iquery;
    }
  	if(inx<0) {
        inx=url.length();
    }
  	return inx;
  }

  /**
	 * 写入数据到指定目录的指定名称的文件
	 * @param writePath 指定写入目录，若不存在则自动创建
	 * @param fileName 指定文件的文件名称
	 * @param writeData 需要写入的数据，以utf-8的编码写入
	 */
	public static void writeFileToPath(String writePath,String fileName,String writeData)
	{
		File filePt = new File(writePath);
		if(!filePt.exists() || !filePt.isDirectory())
		{
			filePt.mkdirs();
		}
		File file = new File(writePath+"/"+fileName);
		BufferedOutputStream buff = null;
		try
		{
			buff = new BufferedOutputStream(new FileOutputStream(file));
			buff.write(writeData.getBytes("UTF-8"));
			buff.flush();
		}
		catch(Exception e)
		{
			ExceptionUtil.log(e);
			e.printStackTrace();
		}
		finally {
			try {
				if(buff!=null) {
                    buff.close();
                }
			} catch (Exception e) {
				ExceptionUtil.log(e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 重构字符流文件的读文件，并根据对应的编码转换为对应的字符串。
	 * @param filePath 文件路径
	 * @param encodeType 编码类型（空：不重新编码）
	 */
	public static String file2String(String filePath, String encodeType){
		StringBuilder result = new StringBuilder();
		File file = new File(filePath);
		//判断文件是否存在
		if(file.exists()){
			try{
				//构造一个BufferedReader类来读取文件
				BufferedReader br;
				if(CheckUtil.isNullorEmpty(encodeType)){
					br = new BufferedReader(new FileReader(file));
				}else{
					br = new BufferedReader(new InputStreamReader(new FileInputStream(file),encodeType));
				}
				String s = null;
				//使用readLine方法，一次读一行
				while((s = br.readLine())!=null){
					result.append(System.lineSeparator()+s);
				}
				br.close();
			}catch(Exception e){
				ExceptionUtil.log(e);
				e.printStackTrace();
			}
		}
		return result.toString();
	}
	/**
	 * 从指定路径的文件中读取文本数据
	 * @param filePath 指定路径下的文件
	 * @return 指定路径的文件内容
	 */
	public static String file2String(String filePath){
		return file2String(filePath, null);
	}

	/**
	 * 从指定路径的文件中读取文本数据
	 * @param filePath 指定路径下的文件
	 * @return 指定路径的文件内容(UTF-8)
	 */
	public static String file2String_utf8(String filePath){
		return file2String(filePath, "UTF-8");
	}
	/**
	 * 将文件(夹)名字转换成安全的名字，/\\\\<>:?*| 这些特殊符会转换为＠符
	 * @param fname 不能为空
	 * @return
	 */
	public static String safeFileName(String fname)
	{
		return fname.replaceAll("[/\\\\<>:?*|]", "@");
	}
	
	/**
	 * 判断文件扩展名是否属于白名单，如果是，抛出错误
	 * @param filePath 包含扩展名的文件名，或直接是扩展名（包含.）
	 * @param whiteFileExt 则使用默认的白名单，如果为空，则使用默认的白名单
	 * @return 如果不是白名单扩展名，原样返回，否则抛出错误。
	 */
	public static String fileWhitelist(String filePath, String[] whiteFileExt) {
		if (whiteFileExt == null || whiteFileExt.length == 0) {
			whiteFileExt = DEFAULT_BLACK_FILE_EXT;
		}
		String sExt = com.qitai.utils.FileUtil.getExtend(filePath).toLowerCase();
		if (!sExt.isEmpty() && !CheckUtil.contains(whiteFileExt, sExt)) {
			throw new FileNotAcceptException("不允许上传此种类型文件：" + filePath);
		}
		return filePath;
	}
	
	/**
	 * 在最后需要关闭流对象时，调用此方法关闭（内部会屏蔽关闭时产生的异常）
	 * @param handle
	 */
	public static <T extends AutoCloseable> void tryCloseStream(T handle)
	{
		try{
			if(handle!=null) {
                handle.close();
            }
		}catch(Throwable e){
			//关闭流，如果出错，不做任何处理
		}
	}

	/**
	 * 判断文件大小
	 *
	 * @param len 文件长度
	 * @param size 限制大小
	 * @param unit 限制单位（B,K,M,G）
	 * @return
	 */
	public static void checkFileSize(long len, int size, String unit) {
		double fileSize = 0;
		if ("B".equals(unit.toUpperCase())) {
			fileSize = (double) len;
		} else if ("K".equals(unit.toUpperCase())) {
			fileSize = (double) len / 1024;
		} else if ("M".equals(unit.toUpperCase())) {
			fileSize = (double) len / 1048576;
		} else if ("G".equals(unit.toUpperCase())) {
			fileSize = (double) len / 1073741824;
		}
		if (fileSize > size) {
			throw new ServiceException("用户上传附件大小不能大于" + size + unit);
		}
	}

	/**
	 * 文件大小
	 *
	 * @param size 长度
	 * @return String
	 */
	public static String getSize(float size) {
		//获取到的size为：1705230
		//定义GB的计算常量
		int gb = 1024 * 1024 * 1024;
		//定义MB的计算常量
		int mb = 1024 * 1024;
		//定义KB的计算常量
		int kb = 1024;
		//格式化小数
		DecimalFormat df = new DecimalFormat("0.0");
		String resultSize = "";
		if (size / gb >= 1) {
			//如果当前Byte的值大于等于1GB
			resultSize = df.format(size / (float) gb) + "GB";
		} else if (size / mb >= 1) {
			//如果当前Byte的值大于等于1MB
			resultSize = df.format(size / (float) mb) + "MB";
		} else if (size / kb >= 1) {
			//如果当前Byte的值大于等于1KB
			resultSize = df.format(size / (float) kb) + "KB";
		} else {
			resultSize = size + "B   ";
		}
		return resultSize;
	}
	public static String getConvertM(long size){
		//定义MB的计算常量
		int mb = 1024 * 1024;
		//格式化小数
		DecimalFormat df = new DecimalFormat("0");
		return df.format(size / (float) mb);
	}
	/**
	 * InputStream 转 File
	 *
	 * @param ins 输入流
	 * @param file 文件路径
	 */
	public static void inputstreamToFile(InputStream ins, File file) throws Exception {
		try (OutputStream os = new FileOutputStream(file)) {
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		}
	}

	/**
	 * inputStream转化为byte[]数组
	 *
	 * @param input 输入流
	 * @return byte[]
	 */
	public static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}
}
