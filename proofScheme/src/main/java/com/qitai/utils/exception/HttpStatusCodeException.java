package com.qitai.utils.exception;

/**
 * http请求，4xx~5xx的返回结果，抛出异常.
 *
 */
public class HttpStatusCodeException extends RuntimeException
{
	private static final long serialVersionUID = 6587204538147967774L;

	private int statusCode;
	
	/**
   * 构造函数.
   * 
   * @param statusCode http请求返回的状态码
   * @param content http请求返回的内容
   */
  public HttpStatusCodeException(int statusCode, String content)
  {
  	//添加err标签，让客户端更准确找到错误信息
    super(content);
    this.statusCode=statusCode;
  }

  /**
   * 获取http status code
   * @return
   */
	public int getStatusCode()
	{
		return statusCode;
	}
	
//	/**
//	 * 设置http status code
//	 * @param statusCode
//	 */
//	public void setStatusCode(int statusCode)
//	{
//		this.statusCode = statusCode;
//	}
}
