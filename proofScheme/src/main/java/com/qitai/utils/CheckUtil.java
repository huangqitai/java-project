package com.qitai.utils;

import com.qitai.utils.ConvertUtil;
import com.qitai.utils.exception.ExceptionUtil;

import java.io.File;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * 值格式检查，以及合法性检查
 * */
public final class CheckUtil
{
	/**
	 * 检查字符串是否为邮箱。
	 * 
	 * @author frj
	 * @param sAddr
	 * @return
	 */
	public static boolean isEmail(String sAddr)
	{
		Pattern pattern = Pattern
				.compile("^([a-z0-9A-Z]+[-|_\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		if (sAddr != null)
		{
			return pattern.matcher(sAddr).matches();
		}
		return false;
	}

	/**
	 * 判断可遍历集合是否包含指定元素
	 * @param list
	 * @param item
	 * @return
	 */
	public static <T> boolean contains(Enumeration<T> list, T item)
	{
		if(list==null) return false;
		while(list.hasMoreElements()){
			Object oVal=list.nextElement();
			if(oVal==item){//对象引用相同，或同为null
				return true;
			}else if(oVal!=null && oVal.equals(item)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断数组是否包含指定元素
	 * @param arr
	 * @param item
	 * @return
	 */
	public static <T> boolean contains(T[] arr, T item)
	{
		if(arr==null) return false;
		for(T oVal : arr){
			if(oVal==item){//对象引用相同，或同为null
				return true;
			}else if(oVal!=null && oVal.equals(item)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 找出字符串数组内的指定元素所在索引位置
	 * @param list
	 * @param item
	 * @return
	 */
	public static int indexOf(String[] list, String item)
	{
		if(list==null || list.length==0) return -1;
		for(int ix=0;ix<list.length;++ix){
			if(list[ix]==null){
				if(item==null) return ix;
			}
			else if((item==list[ix]) || list[ix].equals(item))
				return ix;
		}
		return -1;
	}
	
	/**
	 * 检果是否为空或""字符串
	 * @param value
	 * @return
	 */
	public static boolean isNullorEmpty(String value)
	{
		return value == null || value.isEmpty();
	}
	
	/**
	 * 检查集合是否为空（null 或 empty）
	 * @param value
	 * @return
	 */
	public static boolean isNullorEmpty(Collection<?> value)
	{
		return value == null || value.isEmpty();
	}
	
	/**
	 * 检查Map是否为空（null 或 empty）
	 * @param value
	 * @return
	 */
	public static boolean isNullorEmpty(Map<?,?> value)
	{
		return value == null || value.isEmpty();
	}
	
	/**
	 * 判断一个数值封装类是否为null或值为0
	 * @param value
	 * @return
	 */
	public static <T extends Number> boolean isNullorZero(T value)
	{
		return value==null || (value.doubleValue()==0);
	}

	
	/**
	 * 判断是否有效ID串。不为空，且不以#开头。
	 * @param id 要判断的标识值。
	 * @return
	 */
	public static boolean isValidId(String id)
	{
		if(id==null || id.isEmpty()) return false;
		if(id.charAt(0)=='#') return false;
		return true;
	}
	
	/**
	 * 判断查询参数的比较符，是否为like符，或=号。一般用于id查找的sql语句。
	 * @param sql 要判断的原始语句
	 * @param pn 参数名。传入一般的值有“?”、“?n”（n为序号的查询参数，如：?1）、“:name”（name为命名的查询参数，如：:uid）
	 * @return 0，两者都不是，1是like符，-1是等号
	 */
	@SuppressWarnings("deprecation")
	public static int isWhereLikeOrEquals(String sql,String pn)
	{
		int iret=0;
    if(sql!=null){
    	if(sql.indexOf('\'')>=0)
    		sql=ConvertUtil.removeSqlStringConst(sql,false);
    	
    	int ipos=sql.lastIndexOf(pn)-1;
    	while(ipos>=0){
    		if(Character.isSpace(sql.charAt(ipos))) ipos--;
    		else if(sql.charAt(ipos)=='=') {iret=-1; break;}
    		else if(isLike(sql,ipos)) {iret=1; break;}
    		else ipos=sql.lastIndexOf(pn,ipos)-1;
    	}
    }
    return iret;
	}
  //从指定位置向前推，是like关键字
  @SuppressWarnings("deprecation")
	private static boolean isLike(String sql, int ipos)
	{
		if(ipos<5) return false;
		if(!Character.isSpace(sql.charAt(ipos-4))) return false;
		char chx=sql.charAt(ipos);
		if(chx!='e' && chx!='E') return false;
		if(!"lik".equalsIgnoreCase(sql.substring(ipos-3, ipos))) return false;
		return true;
	}

	/**
	 * 判断是否为中文字符
	 * 
	 * @param chv
	 * @return
	 */
	public static boolean isCNumber(char chv)
	{
		// 由于汉字0可能会用到两个编码中的一个，需要全部判断
		return (chv == '○' || chv == '一' || chv == '二' || chv == '三'
				|| chv == '四' || chv == '五' || chv == '六' || chv == '七'
				|| chv == '八' || chv == '九' || chv == '〇');
	}
	
	/**
	 * 判断指定字符串是否为全数字
	 * @param val
	 * @return
	 */
	public static boolean isDigit(String val)
	{
		if(val==null || val.isEmpty()) return false;
		for(char chx:val.toCharArray()){
			if(!Character.isDigit(chx)) return false;
		}
		return true;
	}
	
	/**
	 * 收集时间差，向控件台输出时长
	 * @param start 开始时间点（等于0表示第一次调用，不输出）。
	 * @param title 要显示时间的标题文本（start=0时，忽略）。
	 * 控件台输出内容为："XXXX用时：yyyms"。XXXX为title内容，yyy为当前时间点与start时间点的差值
	 * @return 下一次计算的开始时间点（做为下一次调用的start参数值）
	 */
	public static long checkTime(long start,String title)
	{
		long next=System.currentTimeMillis();
		// *#if DEBUG
		if(start>0){
			//0=getStackTrace,1=当前方法,2=调用当前方法的方法
			System.err.println(String.format("[%s]%s用时：%d ms", 
					Thread.currentThread().getStackTrace()[2].getMethodName(), title, (next-start)));
		}
		//#endif */
		return next;
	}
	
	/** 
   * 判断某个类里是否有某个方法 
   * @param clazz 类
   * @param methodName 方法名
   * @author wushanghai
   * @return
   */
  public static boolean isHaveSuchMethod(Class<?> clazz, String methodName){
      Method[] methodArray = clazz.getMethods();
      boolean result = false;
      if(null != methodArray){
          for(int i = 0;i < methodArray.length;i++){
              if(methodArray[i].getName().equals(methodName)){
                  result = true;
                  break;
              }
          }
      }
      return result;
  }
  
  /**
   * 判断指定字符串是否合法日期
   * @param str 指定字符串
   * @param dateFormat 判断合法日期的格式，例如yyyy-MM-dd或yyyy-MM-dd HH:mm:ss
   * @return
   */
  public static boolean isValidDate(String datevalue, String dateFormat) {
		boolean convertSuccess=true;
		//根据指定日期格式校验
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		try {
			//设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2018-02-29会被接受，并转换成2018-03-01
			format.setLenient(false);
			format.parse(datevalue);
		} catch (ParseException e) {
			ExceptionUtil.log(e);
			//如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess=false;
		}
		return convertSuccess;
	}
	/**
	 * 判断运行系统是linux还是windows,true為Linux，false為windows
	 */
	public static boolean isLinux() {
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		if (os != null && os.toLowerCase().contains("linux")) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判断运行系统的盘符是否存在，true 为存在
	 * @param driveName：e:\
	 */
	public static boolean isSystemDriveExists(String driveName) {
		File f = new File(driveName);
		if(f.exists()){
			return true;
		}else{
			return false;
		}
	}
}
