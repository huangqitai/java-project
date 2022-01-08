package com.qitai.utils;

import cn.hutool.db.meta.Column;
import com.qitai.utils.exception.ExceptionUtil;
import oracle.sql.CLOB;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 提供各种不同类型间的转换，一般是字符串到指定类型
 * @author dennis
 *
 */
public final class ConvertUtil
{

	public static String exceptionToString(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		e.printStackTrace(pw);
		pw.flush();
		sw.flush();
		return sw.toString();
	}
	
	/**
	 * 截取指定分隔符的左边子串，如果分隔符不存在，则整个字符串返回
	 * @param src 要截取的字符串
	 * @param split 分隔符
	 * @return
	 */
	public static String leftSubStr(String src,String split)
	{
		if(CheckUtil.isNullorEmpty(src)) {
			return "";
		}
		int ipos=src.indexOf(split);
		if(ipos>=0) {
			return src.substring(0, ipos);
		}
		return src;
	}
	
	/**
	 * 截取指定分隔符的右边子串，如果分隔符不存在，则返回空
	 * @param src 要截取的字符串
	 * @param split 分隔符
	 * @return
	 */
	public static String rightSubStr(String src,String split)
	{
		if(CheckUtil.isNullorEmpty(src)) {
			return "";
		}
		int ipos=src.indexOf(split);
		if(ipos<0) {
			return "";
		}
		return src.substring(ipos+1);
	}

	/**
	 * 移除字符串str中指定字符 strStart、strEnd之间的字符串
	 *
	 * @param body     要截取的字符串
	 * @param strStart 开始的字符串
	 * @param strEnd   结束的字符串
	 * @return String
	 */
	public static String subRangeString(String body, String strStart, String strEnd) {
		while (true) {
			int index1 = body.indexOf(strStart);
			if (index1 != -1) {
				int index2 = body.indexOf(strEnd, index1);
				if (index2 != -1) {
					body = body.substring(0, index1) + body.substring(index2 + strEnd.length(),body.length());
				} else {
					return body;
				}
			} else {
				return body;
			}
		}
	}

	/**
	 * 截取字符串str中指定字符 strStart、strEnd之间的字符串
	 * @param str 要截取的字符串
	 * @param strStart 开始的字符串
	 * @param strEnd 结束的字符串
	 * @return String
	 */
	public static String subString(String str, String strStart, String strEnd) {
		int strStartIndex = str.indexOf(strStart);
		int strEndIndex = str.indexOf(strEnd);
		// index为负数即表示该字符串中没有该字符
		if (strStartIndex < 0) {
			return null;
		}
		if (strEndIndex < 0) {
			return null;
		}
		return str.substring(strStartIndex, strEndIndex).substring(strStart.length());
	}
	/**
	 * 将字节数组转换为十六进制表示的字符串(不够两位会补0)。
	 * @param byBuf 要转换的字节数组。
	 * @return
	 */
	public static String byteToString(byte[] byBuf)
	{
		return byteToString(byBuf,true);
	}
	/**
	 * 将字节数组转换为十六进制表示的字符串。
	 * @param byBuf 要转换的字节数组。
	 * @param bPrefixZero 是否带前缀0（一个字节两个字符，如果小于16时，指出是否补0对齐）。
	 * 如：值为0xA时，当此参数为true，返回"0A"；为false，返回"A"
	 * @return 转换后的字符串，如果出错，返回null
	 */
	public static String byteToString(byte[] byBuf, boolean bPrefixZero)
	{
		if(byBuf==null) {
			return null;
		}
		StringBuilder sb=new StringBuilder();
		String sNum;
		for (int i = 0; i < byBuf.length; i++)
		{
			sNum=Integer.toHexString(byBuf[i]&0xFF);
			if(sNum.length()==1 && bPrefixZero) {
				sb.append("0");
			}
			sb.append(sNum);
		}
		return sb.toString();
	}
	
	/**
	 * 将指定字符串重复多次，生成新字符串返回
	 * @param chr 要重复的字符（或字符串）
	 * @param iCount 重复记数（如果小于等于0，则返回""）
	 * @param split 重复间的分隔符（如果不需要，则可以设为null或""）
	 * @return
	 */
	public static String RepeatrChar(String chr, int iCount, String split)
	{
		if(iCount<=0) {
			return "";
		}
		if(iCount==1) {
			return chr;
		}
		StringBuilder sb=new StringBuilder();
		if(split==null) {
			split="";
		}
		for(int ix=0;ix<iCount;++ix){
			sb.append(split);
			sb.append(chr);
		}
		if(!split.isEmpty()){
			sb=sb.delete(0, split.length());
		}
		return sb.toString();
	}
//	public static String RepeatrChar(char chr, int iCount, char split)
//	{
//		if(iCount<=0) return "";
//		if(iCount==1) return Character.toString(chr);
//		StringBuilder sb=new StringBuilder();
//		for(int ix=0;ix<iCount;++ix){
//			if(split!=(char)0)
//				sb.append(split);
//			sb.append(chr);
//		}
//		if(split!=(char)0)
//			sb=sb.deleteCharAt(0);
//		return sb.toString();
//	}

	/**
	 * 将字符串内容进行CSV内容编码（类似HTML编码风格）：
	 * 回车换行符转换为&#13;&#10;，双引号转换为&quot;，逗号转换为&#44;，&符转换为&amp;
	 * @param sOrgVal 要编码的字符串
	 * @return 返回编码后的字符串，如果为空，返回Empty。
	 */
	public static String toCsvEncode(String sOrgVal)
	{
		if(CheckUtil.isNullorEmpty(sOrgVal)) {
			return "";
		}
		return sOrgVal.replaceAll("&", "&amp;")
				.replaceAll("\\r","&#13;").replaceAll("\\n","&#10;")
				.replaceAll(",","&#44;").replaceAll("\"","&quot;");
	}
	
	/**
	 * 将以CSV编码（类似HTML编码风格）的字符串内容解码，
	 * 要解码的字符见{@code toCsvEncode}描述。
	 * @param sCsvVal 要解码的字符串
	 * @return 返回解码后的字符串，如果为空，返回Empty。
	 */
	public static String fromCsvEncode(String sCsvVal)
	{
		if(CheckUtil.isNullorEmpty(sCsvVal)) {
			return "";
		}
		
		return sCsvVal.replaceAll("&#13;","\r").replaceAll("&#10;","\n")
				.replaceAll("&#44;",",").replaceAll("&quot;","\"")
				.replaceAll("&amp;", "&");
	}
	
	/**
	 * 将字符串转换成可做为ID使用的字符串（只包含下划线、字母、数字、或中文）<br>
	 * 原则：数字上的符号，用数字转意；纯符号键，从左到右，从上到下依次按键盘字母从左到右上下两排<br>
	 * 最后p与z结对，而多出一对符号再用最后一排字母键从左到右结对（比如：xc vb nm）。对应关系如下：<br>
	 * !@#$%^&*()   ~`   _-   +=   {[   }]   |\   :;   "'   &lt;,   &gt;.   ?/<br>
	 * 1234567890   qa   ws   ed   rf   tg   yh   uj   ik   ol   pz   xc<br>
	 * @param text 要转换的字符串
	 * @return
	 */
	public static String toIdEncode(String text)
	{
		if(CheckUtil.isNullorEmpty(text)) {
			return "";
		}
		
		return text.replace("_", "_w")
				.replace("~", "_q")
				.replace("!", "_1").replace("@", "_2").replace("#", "_3").replace("$", "_4")
				.replace("-", "_s").replace("\\", "_h").replace(":", "_u").replace("<", "_o")
				.replace(">", "_p").replace(".", "_z")
				.replace("?", "_x").replace("/", "_c");
	}
	
	/**
	 * 将可做为ID使用的字符串转换成原字符串
	 * @param text
	 * @return
	 */
	public static String fromIdEncode(String text)
	{
		if(CheckUtil.isNullorEmpty(text)) {
			return "";
		}
		
		return text.replace("_q", "~")
				.replace("_1", "!").replace("_2", "@").replace("_3", "#").replace("_4", "$")
				.replace("_s", "-").replace("_h", "\\").replace("_u", ":").replace("_o", "<")
				.replace("_p", ">").replace("_z", ".")
				.replace("_x", "?").replace("_c", "/")
				.replace("_w", "_");
	}
	
	/**
	 * 为了能将url放入html或js中，对特殊字符进行url编码：＜、＞、'、"、\ 共5个字符
	 * @param text
	 * @return
	 */
	public static String toUrlEncode(String text)
	{
		if(CheckUtil.isNullorEmpty(text)) {
			return "";
		}
		
		return text.replace("<", "%3C")
			.replace(">", "%3E").replace("'", "%27")
			.replace("\"", "%22").replace("\\", "%5C");
	}

	/**
	 * 转换为HTML编码格式常量字符串值，转换&、"、'、＜、＞、回车换行符
	 * @param text
	 * @return
	 */
	public static String toHtmlEncode(String text)
	{
		if(CheckUtil.isNullorEmpty(text)) {
			return "";
		}
		
		return text.replace("&", "&amp;")
			.replace("\"", "&quot;").replace("'", "&apos;")
			.replace("<", "&lt;").replace(">", "&gt;")
			.replace("\r","&#13;").replace("\n","&#10;");
	}
	
	/**
	 * 移除字符串内html特殊字符，主要包括尖括号，引号(" 或 ')，斜线(\)，And符 和 回车换行符
	 * @param text
	 * @return
	 */
	public static String removeHtmlChar(String text)
	{
		if(CheckUtil.isNullorEmpty(text)) {
			return "";
		}
		return text.replaceAll("[<>\\\"\\\'\\\\&\r\n]", "");
	}
	
	/**
	 * 移除字符串内脚本标签内容＜script|＜/script，替换成＜err
	 * @param text
	 * @return
	 */
	public static String removeScriptTag(String text)
	{
		if (!CheckUtil.isNullorEmpty(text)) {
			return text.replaceAll("(?i)(<script|</script)", "<err");
		}
		return "";
	}
	
	/**
	 * 转换为js常量字符串值（\、"、'、回车换行符）
	 * @param text
	 * @return
	 */
	public static String toJs(Object text){
		if(text==null) {
			return "";
		}
		String temp=text.toString();
		if(temp.isEmpty()) {
			return "";
		}
		
		temp = temp.replace("\\", "\\\\")
			 .replace("\"", "\\\"").replace("'", "\\'")
			 .replace("\r", "\\r").replace("\n", "\\n");
		return temp;
	}
	
	/**
	 * 转换为sql常量字符串值（替换'为''）。
	 * @param text
	 * @return 如果为空（“”）或null，返回“”
	 */
	public static String toSql(String text){
		if(CheckUtil.isNullorEmpty(text)) {
			return "";
		}
		return text.replace("'", "''");
	}
	
	/**
	 * 移除sql语句中的字符串常量内容。主要方便后续的其它处理
	 * @param sql 要处理的sql语句，会将单引号包含的内容将每个字符替换成*。
	 * @param saveQuot 是否要保留包含内容的本单引号（内容中的单引号仍会被替换），如果为false，则单引号也会被替换。
	 * @return
	 */
	public static String removeSqlStringConst(String sql,boolean saveQuot)
	{
		if(CheckUtil.isNullorEmpty(sql)) {
			return "";
		}
		StringBuilder sbSql=new StringBuilder(sql.length());
		
		boolean bIn=false;
		for(int inx=0;inx<sql.length();++inx){
			char chx=sql.charAt(inx);
			if(chx!='\''){
				if(bIn) {
					sbSql.append('*');
				} else {
					sbSql.append(chx);
				}
			}
			else if(bIn){
				int iback=inx+1;
				if(sql.length()==iback || sql.charAt(iback)!='\''){
					sbSql.append(saveQuot?chx:'*');
					bIn=false;
				}else{
					sbSql.append("**");
					inx=iback;
				}
			}else{
				sbSql.append(saveQuot?chx:'*');
				bIn=true;
			}
		}
		return sbSql.toString();
	}
	
	/**
	 * 转换为正则表达式字符，对\、/、"、(、)等添加转意符。
	 * @param text
	 * @return
	 */
	public static String toRegex(String text)
	{
		if(CheckUtil.isNullorEmpty(text)) {
			return "";
		}
		text = text.replace("\\", "\\\\")
			.replace("/", "\\/").replace("\"", "\\\"").replace("|", "\\|")
			.replace(".", "\\.").replace("?", "\\?").replace("*", "\\*").replace("+", "\\+")
			.replace("(", "\\(").replace(")", "\\)").replace("{", "\\{").replace("}", "\\}")
			.replace("[", "\\[").replace("]", "\\]")
			.replace("^", "\\^").replace("$", "\\$");
		return text;
	}


	/**
	 * 替换特殊符号
	 * 特殊符号/ \ : * ? " < > | / \无法用来做文件命名的
	 * @param text
	 * @return
	 */
	public static String toReplaceSpecificSymbol (String text)
	{
		if(CheckUtil.isNullorEmpty(text)) {
			return "";
		}
		text = text.replace("\\", "")
				.replace("/", "").replace(":", "").replace("*", "")
				.replace("?", "").replace("\"", "").replace("<", "").replace(">", "")
				.replace("|", "").replace("/", "").replace("\\", "");
		return text;
	}
	
	/**
	 * 转换为字符串值。
	 * 如果是Date/Calendar/UUID类型，将返回标准格式的字符串；
	 * 如果是byte[]，将返回Base64编码串。
	 * 其他类型，返回toString的值
	 * @param ov
	 * @param sDefault
	 * @return
	 */
	public static String getValue(Object ov, String sDefault)
	{
		if (ov == null) {
			return sDefault;
		}
		if (ov instanceof String) {
			return (String)ov;
		}
		if (ov instanceof Date) {
			return DateUtil.dateToString((Date)ov);
		}
		if(ov instanceof Calendar) {
			return DateUtil.dateToString(((Calendar)ov).getTime());
		}
		if (ov instanceof UUID) {
			return UUIDHelper.getString((UUID)ov);
		}
		if (ov instanceof byte[]) {
			return Base64.getEncoder().encodeToString((byte[])ov);
		}
		//添加clob类型的格式装换 tmg 2019-01-18
		if(ov instanceof CLOB){
			String result = sDefault;
			try{
				CLOB clob = (CLOB)ov;
				result = clob.getSubString((long)1, (int) clob.length());
			}catch(Exception e) {

				System.out.println(e.getMessage());
			}
			return result;
		}

		
		return ov.toString();
	}
	/**
	 * 尝试转换成整数。如果出错，返回指定默认值。
	 * Double/Float值，将截取整数部分；
	 * 
	 * @param ov
	 * @param iDefault
	 * @return
	 */
	public static int getValue(Object ov, int iDefault)
	{
		if (ov == null) {
			return iDefault;
		}
		if (ov instanceof Integer) {
			return (int)ov;
		}
		if(ov instanceof Short) {
			return (short)ov;
		}
		if(ov instanceof Long) {
			return (int)(long)ov;//(dennis)可能会溢出
		}
		if(ov instanceof BigInteger) {
			return ((BigInteger)ov).intValue();
		}
		if(ov instanceof BigDecimal) {
			return (int)((BigDecimal)ov).doubleValue();
		}
		if(ov instanceof Double) {
			return (int)(double)ov;
		}
		if(ov instanceof Float) {
			return (int)(float)ov;
		}
		if(ov instanceof Byte) {
			return (byte)ov;
		}
		if(ov instanceof Boolean) {
			return ((boolean)ov)?1:0;
		}
		
		try
		{
			String sVal=getValue(ov, "").replace(",","");
			if (sVal.indexOf(".") >= 0)
			{
				//截断取整（不四舍五入）
				return (int)Double.parseDouble(sVal);
			}
			else
			{
				return Integer.parseInt(sVal);
			}
		}
		catch(Exception ex)
		{

			return iDefault;
		}
	}
	/**
	 * 尝试转换成长整数。如果出错，返回指定默认值。
	 * Double/Float值，将截取整数部分；
	 * 
	 * @param ov
	 * @param lDefault
	 * @return
	 */
	public static long getValue(Object ov, long lDefault)
	{
		if (ov == null) {
			return lDefault;
		}
		if (ov instanceof Integer) {
			return (int)ov;
		}
		if(ov instanceof Short) {
			return (short)ov;
		}
		if(ov instanceof Long) {
			return (long)ov;
		}
		if(ov instanceof BigInteger) {
			return ((BigInteger)ov).longValue();
		}
		if(ov instanceof BigDecimal) {
			return (long)((BigDecimal)ov).doubleValue();
		}
		if(ov instanceof Double) {
			return (long)(double)ov;
		}
		if(ov instanceof Float) {
			return (long)(float)ov;
		}
		if(ov instanceof Byte) {
			return (byte)ov;
		}
		if(ov instanceof Boolean) {
			return ((boolean)ov)?1:0;
		}
		
		try
		{
			String sVal=getValue(ov, "").replace(",","");
			if (sVal.indexOf(".") >= 0)
			{
				//截断取整（不四舍五入）
				return (long)Double.parseDouble(sVal);
			}
			else
			{
				return Long.parseLong(sVal);
			}
		}
		catch(Exception ex)
		{

			return lDefault;
		}
	}
	/**
	 * 转换为无限大的整数。注：对于byte[]值，将调用new BigInteger(byte[])
	 * @param ov
	 * @param biDefautl
	 * @return
	 */
	public static BigInteger getValue(Object ov, BigInteger biDefautl)
	{
		if (ov == null) {
			return biDefautl;
		}
		if (ov instanceof Integer) {
			return BigInteger.valueOf((int)ov);
		}
		if(ov instanceof Short) {
			return BigInteger.valueOf((short)ov);
		}
		if(ov instanceof Long) {
			return BigInteger.valueOf((long)ov);
		}
		if(ov instanceof BigInteger) {
			return (BigInteger)ov;
		}
		if(ov instanceof BigDecimal) {
			return ((BigDecimal)ov).toBigInteger();
		}
		if(ov instanceof Double) {
			return BigDecimal.valueOf((double)ov).toBigInteger();
		}
		if(ov instanceof Float) {
			return BigInteger.valueOf((long)(float)ov);
		}
		if(ov instanceof Byte) {
			return BigInteger.valueOf((byte)ov);
		}
		if(ov instanceof Boolean) {
			return BigInteger.valueOf(((boolean)ov)?1:0);
		}
		if(ov instanceof byte[]) {
			return new BigInteger((byte[])ov);
		}
		
		try
		{
			String sVal=getValue(ov, "").replace(",","");
			int iPos=sVal.indexOf(".");
			if (iPos >= 0)
			{
				//截断取整（不四舍五入）
				sVal=sVal.substring(0,iPos);
				if(sVal.isEmpty()) {
					sVal="0";
				}
			}
			return new BigInteger(sVal);
		}
		catch(Exception ex)
		{

			return biDefautl;
		}
	}
	/**
	 * 尝试转换成Double。如果出错，返回指定默认值。
	 * 
	 * @param ov
	 * @param dDefault
	 * @return
	 */
	public static double getValue(Object ov, double dDefault)
	{
		if (ov == null) {
			return dDefault;
		}
		if (ov instanceof Integer) {
			return (int)ov;
		}
		if(ov instanceof Short) {
			return (short)ov;
		}
		if(ov instanceof Long) {
			return (long)ov;
		}
		if(ov instanceof BigInteger) {
			return ((BigInteger)ov).doubleValue();
		}
		if(ov instanceof BigDecimal) {
			return ((BigDecimal)ov).doubleValue();
		}
		if(ov instanceof Double) {
			return (double)ov;
		}
		if(ov instanceof Float) {
			return (float)ov;
		}
		if(ov instanceof Byte) {
			return (byte)ov;
		}
		if(ov instanceof Boolean) {
			return ((boolean)ov)?1.0:0.0;
		}
		
		try
		{
			String sVal=getValue(ov, "");
			return Double.parseDouble(sVal);
		}
		catch(Exception ex)
		{

			return dDefault;
		}
	}
	
	/**
	 * 尝试转换成无限大的小数。如果出错，返回指定默认值。注：对于byte[]值，将调用new BigDecimal(new BigInteger(byte[]))
	 * 
	 * @param ov
	 * @param bdDefault
	 * @return
	 */
	public static BigDecimal getValue(Object ov, BigDecimal bdDefault)
	{
		if (ov == null) {
			return bdDefault;
		}
		if (ov instanceof Integer) {
			return BigDecimal.valueOf((int)ov);
		}
		if(ov instanceof Short) {
			return BigDecimal.valueOf((short)ov);
		}
		if(ov instanceof Long) {
			return BigDecimal.valueOf((long)ov);
		}
		if(ov instanceof BigInteger) {
			return new BigDecimal((BigInteger)ov);
		}
		if(ov instanceof BigDecimal) {
			return (BigDecimal)ov;
		}
		if(ov instanceof Double) {
			return BigDecimal.valueOf((double)ov);
		}
		if(ov instanceof Float) {
			return BigDecimal.valueOf((float)ov);
		}
		if(ov instanceof Byte) {
			return BigDecimal.valueOf((byte)ov);
		}
		if(ov instanceof Boolean) {
			return BigDecimal.valueOf(((boolean)ov)?1.0:0.0);
		}
		if(ov instanceof byte[]) {
			return new BigDecimal(new BigInteger((byte[])ov));
		}
		
		try
		{
			String sVal=getValue(ov, "");
			return new BigDecimal(sVal);
		}
		catch(Exception ex)
		{

			return bdDefault;
		}
	}
	/**
	 * 尝试转换成Boolean。如果出错，返回指定默认值。
	 * @param ov
	 * @param bDefault
	 * @return
	 */
	public static boolean getValue(Object ov, boolean bDefault)
	{
		if (ov == null) {
			return bDefault;
		}
		if(ov instanceof Boolean) {
			return (boolean)ov;
		}
		if (ov instanceof Integer) {
			return ((int)ov)!=0;
		}
		if(ov instanceof Short) {
			return ((short)ov)!=0;
		}
		if(ov instanceof Long) {
			return ((long)ov)!=0;
		}
		if(ov instanceof BigInteger) {
			return ((BigInteger)ov).compareTo(BigInteger.ZERO)==0;
		}
		if(ov instanceof BigDecimal) {
			return ((BigDecimal)ov).compareTo(BigDecimal.ZERO)==0;
		}
		if(ov instanceof Double) {
			return ((double)ov)!=0.0;
		}
		if(ov instanceof Float) {
			return ((float)ov)!=0.0;
		}
		if(ov instanceof Byte) {
			return ((byte)ov)!=0;
		}

		try
		{
			String sVal=getValue(ov, "");
			if (sVal.isEmpty()) {
				return bDefault;
			}
			
			return Boolean.parseBoolean(sVal);
		}
		catch(Exception ex)
		{

			return bDefault;
		}
	}
	
	/**
	 * 转换成日期值
	 * @param ov 可以是Date类型，Calendar类型，长整型（时间戳，从1970-1-1以来的毫秒，如果是GMT时间，需要加8小时(28800000ms)），
	 * 其它应为 可格式化日期的字符串类型（包括形如：Mon May 22 2017 21:58:42 GMT+0800 国际标准格式）。<br/>
	 * 注：如果是全数字的字符串，内部将会进行简单判断：<br/>
	 * 如果长度4位或6位或8位或14位，且月/日/时/分/秒的值在有效范围内，将视为yyyy或yyyyMM或yyyyMMdd或yyyyMMddHHmmss格式；<br/>
	 * 否则，将视为时间戳（从1970-1-1以来的毫秒）。<br/>
	 * 如：20170101 视为yyyyMMdd格式的串；<br/>
	 * 20171509 因月份值15不是有效值，则视为时间戳。<br/>
	 * @param dtDefault
	 * @return
	 */
	public static Date getValue(Object ov, Date dtDefault)
	{
		if (ov == null) {
			return dtDefault;
		}

		if(ov instanceof Date) {
			return (Date)ov;
		}
		if(ov instanceof Calendar) {
			return ((Calendar)ov).getTime();
		}
		if(ov instanceof Long) {
			return new Date((long)ov);
		}
		if(ov instanceof BigInteger) {
			return new Date(((BigInteger)ov).longValue());
		}
		if(ov instanceof BigDecimal) {
			return new Date(((BigDecimal)ov).longValue());
		}
		try{
			Date dt = DateUtil.stringToDate(ov.toString());
			if(dt==null) {
				dt=dtDefault;
			}
			return dt;
		}
		catch(Exception ex)
		{

			return dtDefault;
		}
	}

	/**
	 * 对URL格式字符串内容进行UTF-8的URL编码（即/符号不会编码）
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String UriPartEncode(String url) throws Exception
	{
		String[] sPart=url.split("/");
		for(int ix=0;ix<sPart.length;++ix){
			sPart[ix]=URLEncoder.encode(sPart[ix], "utf-8");
		}
		
		return String.join("/", sPart);
	}
	
	/** 
   * 把Map<String,Object>处理成实体类
   * @param clazz     想要的实体类 
   * @param map       包含信息的Map对象 
   * @author wushanghai
   * @return
   */
  @SuppressWarnings("unchecked")
  public static Object mapToObject(Class<?> clazz, Map<String,Object> map){
      if(null == map){
          return null;
      }
      Object entity  = null;
      try {
      	entity = clazz.newInstance();
      } catch (InstantiationException e1) {

          e1.printStackTrace();
      } catch (IllegalAccessException e1) {

          e1.printStackTrace();
      } 
      Method[] methods = clazz.getMethods();
      //存放methods和column对应关系，该关系来自于实体类的 @Column配置
      Map<String, Object> fieldHasColumnAnnoMap = new LinkedHashMap<String, Object>();
      //获取 @Column注解字段
	    Annotation[] annotations = null;
	    for(Method method:methods){
	    	annotations = method.getAnnotations();
	    	for(Annotation an : annotations){
          if(an instanceof Column){
              Column column = (Column)an;
              fieldHasColumnAnnoMap.put(column.getName(),method);
          }
	    	}
	    }
	    for (Map.Entry<String, Object> fieldHasColumnAnno : fieldHasColumnAnnoMap.entrySet()){
	    	String dbName = fieldHasColumnAnno.getKey();
	    	Method method = (Method)fieldHasColumnAnno.getValue();
	    	if(method == null){
	    		continue;
	    	}
	    	String getterName = method.getName();
	    	//获取setter方法名称
	    	String setterName = getterName.replaceFirst("g", "s");
	    	Class<?> methodClass = method.getReturnType();
	    	//真正取得set方法
        Method setMethod = null;
	    	try {
          if(CheckUtil.isHaveSuchMethod(clazz, setterName)){
              if(methodClass == String.class){
                  setMethod = clazz.getMethod(setterName, methodClass);
                  setMethod.invoke(entity, map.get(dbName)!=null?String.valueOf(map.get(dbName)):"");//为其赋值
              }else if(methodClass == Integer.class || methodClass == int.class){
                  setMethod = clazz.getMethod(setterName, methodClass);
                  setMethod.invoke(entity, map.get(dbName)!=null?Integer.parseInt(String.valueOf(map.get(dbName))):null);//为其赋值
              }else if(methodClass == Boolean.class || methodClass == boolean.class){
                  setMethod = clazz.getMethod(setterName, methodClass);
                  setMethod.invoke(entity, Boolean.getBoolean(String.valueOf(map.get(dbName))));//为其赋值
              }else if(methodClass == Short.class || methodClass == short.class){
                  setMethod = clazz.getMethod(setterName, methodClass);
                  setMethod.invoke(entity, map.get(dbName)!=null?Short.parseShort(String.valueOf(map.get(dbName))):null);//为其赋值
              }else if(methodClass == Long.class || methodClass == long.class){
                  setMethod = clazz.getMethod(setterName, methodClass);
                  setMethod.invoke(entity, map.get(dbName)!=null?Long.parseLong(String.valueOf(map.get(dbName))):null);//为其赋值
              }else if(methodClass == Double.class || methodClass == double.class){
                  setMethod = clazz.getMethod(setterName, methodClass);
                  setMethod.invoke(entity, map.get(dbName)!=null?Double.parseDouble(String.valueOf(map.get(dbName))):null);//为其赋值
              }else if(methodClass == Float.class || methodClass == float.class){
                  setMethod = clazz.getMethod(setterName, methodClass);
                  setMethod.invoke(entity, map.get(dbName)!=null?Float.parseFloat(String.valueOf(map.get(dbName))):null);//为其赋值
              }else if(methodClass == BigInteger.class ){
                  setMethod = clazz.getMethod(setterName, methodClass);
                  setMethod.invoke(entity, map.get(dbName)!=null?BigInteger.valueOf(Long.parseLong(String.valueOf(map.get(dbName)))):null);//为其赋值
              }else if(methodClass == BigDecimal.class){
                  setMethod = clazz.getMethod(setterName, methodClass);
                  setMethod.invoke(entity, map.get(dbName)!=null?BigDecimal.valueOf(Long.parseLong(String.valueOf(map.get(dbName)))):null);//为其赋值
              }else if(methodClass == Date.class){
                  setMethod = clazz.getMethod(setterName, methodClass);
                  if(map.get(dbName) == null){
                  	//dbName如果有效进入下面的
                  }
                  else if(map.get(dbName).getClass() == java.sql.Date.class){
                      setMethod.invoke(entity, new Date(((java.sql.Date)map.get(dbName)).getTime()));//为其赋值
                  }else if(map.get(dbName).getClass() == java.sql.Time.class){
                      setMethod.invoke(entity, new Date(((java.sql.Time)map.get(dbName)).getTime()));//为其赋值
                  }else if(map.get(dbName).getClass() == java.sql.Timestamp.class){
                      setMethod.invoke(entity, new Date(((java.sql.Timestamp)map.get(dbName)).getTime()));//为其赋值
                  }
              }
          }  
	      } catch (SecurityException e) {

	          e.printStackTrace();
	      } catch (NoSuchMethodException e) {

	          e.printStackTrace();
	      }   catch (IllegalArgumentException e) {

	          e.printStackTrace();
	      } catch (IllegalAccessException e) {

	          e.printStackTrace();  
	      } catch (InvocationTargetException e) {

	          e.printStackTrace();
	      }
	    }
      return entity;
  }  
  
  /**
   * 实体对象转成Map
   * @param obj 实体对象
   * @return
   */
  @SuppressWarnings("rawtypes")
	public static Map<String, Object> object2Map(Object obj) {
      Map<String, Object> map = new HashMap<>();
      if (obj == null) {
          return map;
      }
      try {
      	Class clazz = obj.getClass();
      	if(clazz!=null){
      		Field[] fields = clazz.getDeclaredFields();
          for (Field field : fields) {
              field.setAccessible(true);
              map.put(field.getName(), field.get(obj));
          }
          //获取父类
          Class superClazz = clazz.getSuperclass();
          if(superClazz!=null){
          	Field[] superFields = superClazz.getDeclaredFields();
          	for (Field field : superFields) {
              field.setAccessible(true);
              map.put(field.getName(), field.get(obj));
            }
          	//若存在二级父类
            Class superSuperClazz = superClazz.getSuperclass();
            if(superSuperClazz!=null){
            	Field[] superSuperFields = superSuperClazz.getDeclaredFields();
            	for (Field field : superSuperFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
              }
            }
          }
      	}
      } catch (Exception e) {

          e.printStackTrace();
      }
      return map;
  }
  
  @SuppressWarnings("rawtypes")
	public static Object convertMap(Class classtype,Map<String,Object> map) throws Exception{
  	BeanInfo beanInfo=Introspector.getBeanInfo(classtype);
  	Object object=classtype.newInstance();
  	PropertyDescriptor[] propertyDescriptors=beanInfo.getPropertyDescriptors();
  	for(PropertyDescriptor propertyDescriptor:propertyDescriptors){
  		String propertyName= propertyDescriptor.getName();
  		if(map.containsKey(propertyName)){
  			Object value=map.get(propertyName);
  			Method method=propertyDescriptor.getWriteMethod();
  			//.invoke(object, value)
  			method.invoke(object,value);
  		}
  	}
  	return object;
  }

	/**
	 * @author zhongquanqing
	 * @description 将map中的所有key转化为小写
	 * @param map
	 * @return java.util.Map<java.lang.String,java.lang.String>
	 */
	public static Map<String, String> toLowerKey(Map<String, String> map) {
		Map<String, String> resultMap = new HashMap<String, String>();
		Set<String> sets = map.keySet();
		for (String key : sets) {
			resultMap.put(key.toLowerCase(), map.get(key));
		}
		return resultMap;
	}

/**
 * @author zhongquanqing
 * @param map
 * @description 将map中的所有key转化为大写
 * @return Map<String, Object>
**/
	public static Map<String, Object> toUpperKey(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Set<String> sets = map.keySet();
		for (String key : sets) {
			resultMap.put(key.toUpperCase(), map.get(key));
		}
		return resultMap;
	}

	/**
	 * @author zhongquanqing
	 * @param resultMapList
	 * @description 将List<Map<String, Object>>中的map类型中的所有key转化为大写
	 * @return ist<Map<String, Object>>
	 **/
	public static List<Map<String, Object>> toUpperKey(List<Map<String, Object>> resultMapList ) {
		for (int i=0;i<resultMapList.size();i++){
			Map<String, Object>resultMap= toUpperKey(resultMapList.get(i));
			resultMapList.remove(i);
			resultMapList.add(i,resultMap);
		}
		return resultMapList;
	}
}