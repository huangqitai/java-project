package com.qitai.utils.exception;

/**
 * 当设置数据库属性时，发现类型不匹配时抛出的异常.
 * 
 * @author 黄科天
 *
 */
public class DuplicateLogonException extends RuntimeException
{
	private static final long serialVersionUID = 4402573915470827957L;

  /**
   * 构造函数.
   * 
   * @param msg 异常消息
   */
  public DuplicateLogonException(String msg)
  {
  	//添加err标签，让客户端更准确找到错误信息
    super(msg);
  }
}
