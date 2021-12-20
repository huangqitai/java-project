package com.qitai.utils;

import com.qitai.utils.CheckUtil;
import com.qitai.utils.FileUtil;
import com.qitai.utils.exception.ExceptionUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil
{
	private static final String OUTSIDE_CONFIG_FILE_NAME = "myconfig.properties";
	
	private static Properties myconfig=null;
	//缓存10秒钟，以便连续调用时，不致于影响性能
	private static long cacheTimeMyCfg=0;

	/**
	 * 获取主应用“mainWeb”的部署物理路径，
	 * 依赖mainWeb/WEB-INF/web.xml中配置的webAppRootKey初始化参数。
	 * 
	 * @return 返回格式(没有后缀/)：
   *  Windows系统：E:/tomcat/webapps/mainWeb
   *  Linux系统：/usr/tomcat/webapps/mainWeb
	 */
	public static String getMainWebRoot()
	{
		String appMainRoot=System.getProperty("appMain.root");
		
		if(!CheckUtil.isNullorEmpty(appMainRoot)){
			appMainRoot=appMainRoot.replace('\\', '/');
			if(appMainRoot.endsWith("/"))
				appMainRoot=appMainRoot.substring(0, appMainRoot.length()-1);
		}
		return appMainRoot;
	}
	
	/**
	 * 获取公共应用“pubWeb”的部署物理路径。
	 * 依赖pubWeb/WEB-INF/web.xml中配置的webAppRootKey初始化参数。
	 * 
	 * @return 返回格式(没有后缀/)：
   *  Windows系统：E:/tomcat/webapps/mainWeb
   *  Linux系统：/usr/tomcat/webapps/mainWeb
	 */
	public static String getPubWebRoot()
	{
		String appMainRoot=System.getProperty("appPub.root");
		
		if(!CheckUtil.isNullorEmpty(appMainRoot)){
			appMainRoot=appMainRoot.replace('\\', '/');
			if(appMainRoot.endsWith("/"))
				appMainRoot=appMainRoot.substring(0, appMainRoot.length()-1);
		}
		return appMainRoot;
	}
	
	//public static String getWorkflowWebRoot()
	//{
	//	return System.getProperty("appWfs.root").replace('\\', '/');
	//}
	
	//public static String getFormWebRoot()
	//{
	//	return System.getProperty("appForm.root").replace('\\', '/');
	//}
	
	/**
	 * 从当前环境下有效的配置文件中读取配置项（先从./myconfig.properties取，不存在再从当前资源目录下config.properties中取）。
	 * @param key
	 * @return 返回配置值（并未去除首尾空格），如果key不存在，则返回null。
	 */
	public static String getValue(String key)
	{
		try{
			Properties prop;
			long curTime=System.currentTimeMillis();
			if(myconfig==null || curTime-cacheTimeMyCfg>10000){
				myconfig=getMyOutFileProperties();
				cacheTimeMyCfg=curTime;
			}
			prop=myconfig;
			
			return prop.getProperty(key);
		}catch(Exception ex){
			ExceptionUtil.log(ex);
			return null;
		}
	}
	
	/**
	 * 获取有效配置（优先从外部统一配置文件myconfig.properties中获取，同时会加载资源目录下的配置文件做为默认配置；<br>
	 * 如果外部统一配置文件不存在，则返回的是资源目录下的config.properties配置）
	 * @return
	 * @throws IOException
	 */
	public static Properties getMyOutFileProperties() throws IOException{
		String path = new File(".").getCanonicalPath() + "/" + OUTSIDE_CONFIG_FILE_NAME;
		Properties prop=getProperties("/config.properties", null, null);
		if(new File(path).exists()){
			prop=getProperties(path,prop);
		}
		return prop;
	}
	
  /**从指定位置读取配置文件
   * @author frj
   * @throws IOException
   * @param path 文件绝对路径。
   * @param propDefault 默认配置（可以为null）
   * */
  private static Properties getProperties(String path,Properties propDefault) throws IOException
  {
    Properties pps = new Properties(propDefault);
    InputStream in = new BufferedInputStream(new FileInputStream(path));
    pps.load(in);
    in.close();
    return pps;
  }
  
  /**从当前Web目录或jar包中读取配置文件，文件不存在会抛出异常。
   * @author dennis
   * @throws IOException
   * @param path 配置文件的相对路径。以/开头时，表示基于classpath，否则表示基于clsObj类所在目录。
   * @param clsObj 要加载配置文件所参照的类对象。配置文件查找方式有以下组合情况：<br>
   *   path以/开头，clsObj==null：基于web目录下的classpath查找<br>
   *   path以/开头，clsObj!=null：优先基于web目录下的classpath查找，
   *       不存在则基于clsObj所在包的classPath<br>
   *   path不以/开头，clsObj==null：[无意义]<br>
   *   path不以/开头，clsObj!=null：相对于clsObj所在目录<br>
   * @param propDefault 默认配置（可以为null）
   * */
  public static Properties getProperties(String path, Class<?> clsObj,Properties propDefault) throws IOException
  {
    Properties pps = new Properties(propDefault);
    InputStream stm=null;
    try{
  	if(clsObj==null)
  		stm= com.qitai.utils.PropertiesUtil.class.getResourceAsStream(path);
  	else
  		stm=clsObj.getResourceAsStream(path);
    if(stm==null)
    {
    	throw new IOException(String.format("%s:file not exists for %s class",
    			path,clsObj==null?"this runtime":clsObj.getName()));
    }
    pps.load(stm);
    }finally{
    	FileUtil.tryCloseStream(stm);;
    }
    return pps;
  }

}
