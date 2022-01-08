package com.qitai.utils.exception;

import com.qitai.utils.CheckUtil;
import com.qitai.utils.exception.ExceptionUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Service层公用的Exception.
 * 
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;
	//默认错误码
	private String errCode="-1";
	
	/**
	 * 初始化服务异常
	 * @param errCode，错误码，可以是数字（如：10011），或命名串，建议全大写，带ERR_前缀（如：ERR_USER_NOT_EXIST）
	 * @param message
	 * @param cause
	 */
	public ServiceException(String errCode,String message, Throwable cause) {
  	//添加err标签，让客户端更准确找到错误信息
		super((message!=null && message.indexOf("[errs]")<0)?("[errs]"+message+"[erre]"):message, cause);
		this.errCode=errCode;
	}
	
	/**
	 * 以异常类名为 错误码
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		this(cause.getClass().getSimpleName(),message,cause);
	}
	
	/**
	 * 以-1错误码初始化，错误消息为“系统内部异常”
	 */
	public ServiceException() {
		this("-1","系统内部异常",(Throwable)null);
	}

	/**
	 * 以-1错误码初始化
	 * @param message 错误消息
	 */
	public ServiceException(String message) {
		this("-1",message,(Throwable)null);
	}

	/**
	 * 初始化错误信息
	 * @param errCode，错误码，可以是数字（如：10011），或命名串，建议全大写，带ERR_前缀（如：ERR_USER_NOT_EXIST）
	 * @param message 错误消息
	 */
	public ServiceException(String errCode,String message) {
		this(errCode,message,(Throwable)null);
	}

	/**
	 * 以异常类名为 错误码，异常的消息内容为 错误消息
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		this(cause.getClass().getSimpleName(),cause.getLocalizedMessage(),cause);
	}
	
	public String getErrCode()
	{
		return errCode;
	}
	public void setErrCode(String errCode)
	{
		this.errCode=errCode;
	}
	
	/**
	 * 获取异常错误信息。去除系统约定的分隔符（[errs],[erre]）
	 * @param errorMsg
	 * @return
	 */
	public static String getMessage(String errorMsg){
		if(errorMsg==null) return "";
		if(errorMsg.startsWith("[errs]")){
			return errorMsg.substring(6, errorMsg.length()-6);
		}
		return errorMsg;
	}
	
	/**
	 * 获取最初异常对象的错误消息
	 * @param e
	 * @return
	 */
	public static String getCauseMessage(Throwable e)
	{
		Throwable ep=null;
		Throwable eNext=e;
		while(eNext!=null){
			ep=eNext;
			eNext=ep.getCause();
		}
		String sErr="";
		if(ep!=null){
			sErr=ep.getLocalizedMessage();
			if(CheckUtil.isNullorEmpty(sErr))
				sErr=e.getLocalizedMessage();
		}
		return sErr;
	}
	
	/**
	 * 输出异常对象类名、错误信息及堆栈信息。也含原始异常对象类名及错误信息
	 * @param e
	 * @return
	 */
	public static String getStackTrace(Throwable e)
	{
		if(e==null) return "";
		
		StringBuilder sb = new StringBuilder();
		//异常名
		sb.append(e.toString());
		sb.append("\n");
		//调用栈信息（最多25条）
		StackTraceElement[] stack = e.getStackTrace();
		int proxySize = stack.length;
		int iOmitted=0;
		if(proxySize>25){//多于25条时，只输出前20条
			iOmitted=proxySize-20;
			proxySize=20;
		}
		for(int i = 0;i < proxySize;i++){
			sb.append("- ");
			sb.append(stack[i].toString());
			sb.append(",\r\n");
		}
		if(iOmitted>0){
			sb.append("... ");
			sb.append(iOmitted);
			sb.append(" common frames omitted\r\n");
		}
		
		Throwable ep=null;
		Throwable eNext=e.getCause();
		while(eNext!=null){
			ep=eNext;
			eNext=ep.getCause();
		}
		if(ep!=null){
			sb.append("最初始的异常：");
			sb.append(ep.toString());
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取异常栈字符串信息
	 * 
	 * @param e
	 *          异常
	 * @return 异常栈信息字符串
	 */
	@SuppressWarnings("unused")
	public static String getExceptionPrintStackTrace(Exception e)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try
		{
			e.printStackTrace(pw);
			return sw.toString();
		}
		finally
		{
			try
			{
				sw.close();
				pw.close();
			}
			catch(IOException e1)
			{

				e1.printStackTrace();
			}
		}
	}
	
}