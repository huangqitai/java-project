package com.qitai.utils;

import com.qitai.utils.CheckUtil;
import com.qitai.utils.ConvertUtil;
import com.qitai.utils.exception.ExceptionUtil;
import com.qitai.utils.exception.ServiceException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具类
 * @author HuangLeibing
 *
 */
public final class DateUtil {

	/**
	 * 长日期格式（日期+时间）
	 */
	public static final String FULL_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 短日期格式（无时间）
	 */
	public static final String ONLY_DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 仅时间格式
	 */
	public static final String ONLY_TIME_FORMAT = "HH:mm:ss";
	
	/**
	 * 转换成标准日期格式字符串(包括日期部分，时间部分)
	 * @param dtv
	 * @return
	 */
	public static String dateToString(Date dtv)
	{
		return dateToString(dtv,false);
	}
	
	/**
	 * 按指定格式转换日期。
	 * @param dtv
	 * @param sFmt 指定转换格式：yyyy=年，MM=月，dd=日，HH=小时，mm=分钟，ss=秒。
	 * 注意：对于系统定义的表单字段，可能会传入"needed"
	 * @return
	 */
	public static String dateToString(Date dtv, String sFmt)
	{
		if(CheckUtil.isNullorEmpty(sFmt) || sFmt.equals("needed"))
			sFmt=FULL_DATETIME_FORMAT;
		try{
			SimpleDateFormat sdf=new SimpleDateFormat(sFmt);
			return sdf.format(dtv);
		}catch(Exception ex){

			SimpleDateFormat sdf=new SimpleDateFormat(FULL_DATETIME_FORMAT);
			return sdf.format(dtv);
		}
//		if(!sFmt.equals("needed"))
//		{
//			SimpleDateFormat sdf=new SimpleDateFormat(sFmt);
//			return sdf.format(dtv);
//		}
//		else {
//			return dtv.toString();
//		}

	}

	/**
	 * 转换成标准日期格式字符串
	 * @param dtv
	 * @param bOnlyDate 是否仅转换日期部分（不会返回时间部分）。
	 * @return
	 */
	public static String dateToString(Date dtv, boolean bOnlyDate)
	{
		if(dtv==null) return "";
		return dateToString(dtv,bOnlyDate?ONLY_DATE_FORMAT:FULL_DATETIME_FORMAT);
	}

	/**
	 * 将字符串形式的日期转换成日期。如果不能转换，返回null，转换出错抛出ParseException。<br/>
	 * 注：如果参数是全数字的字符串，内部将会进行简单判断：<br/>
	 * 如果长度8位或14位，且月/日/时/分/秒在有效范围内，将视为yyyyMMdd或yyyyMMddHHmmss格式；<br/>
	 * 否则，将视为时间戳（从1970-1-1以来的毫秒）<br/>
	 * @param sDate 字符串形式日期
	 * @return Date类型日期
	 */
	public static Date stringToDate(String sDate)
	{
		Date date=null;
		if(CheckUtil.isNullorEmpty(sDate)) return date;

		if(CheckUtil.isDigit(sDate)){
			date=isDateDigit(sDate);
			if(date!=null) return date;
			return new Date(Long.parseLong(sDate));
		}


		if(sDate.charAt(0)>='A' && sDate.charAt(0)<='Z') //如果以字母开头，假定为国际格式
		{
			try{
				//(dennis)Date.parse有一个bug，如果GMT西区时，则必须要在年份后，否则会报错
				sDate=fixDateParseZone(sDate);
		  	@SuppressWarnings("deprecation")
				long lr=Date.parse(sDate);
		  	return new Date(lr);
			}catch(Exception ex){

				//如果出错，再进行后继处理
				System.out.println("stringToDate分析【"+sDate+"】出错："+ex.getMessage());
			}
		}

		sDate=formatDateString(sDate);
		date = stringStdToDate(sDate);
		return date;
	}

	/**
	 * 将标准日期格式（yyyy-mm-dd hh:mi:ss，或日期部分，或时间部分）的字符串转换为日期值。
	 * 注：内部不会格式化，也就是说，如果传入的字符串不符合标准格式，会转换出错。<br>
	 * 如果仅传入了日期部分，则时间固定为0:0:0<br>
	 * 如果仅传入了时间部分，则日期固定为1970-1-1
	 * @param sDate 标准格式的日期串
	 * @return
	 */
	public static Date stringStdToDate(String sDate)
	{
		Date date=null;

		SimpleDateFormat df;
		if(sDate.indexOf(':')>0) {
			if(sDate.indexOf('-')>0)
				df = new SimpleDateFormat(FULL_DATETIME_FORMAT);
			else
				df=new SimpleDateFormat(ONLY_TIME_FORMAT);
		} else {
			df = new SimpleDateFormat(ONLY_DATE_FORMAT);
		}
		try{
			date = df.parse(sDate);
		}catch(Exception ex){

			throw new ServiceException(ex);
		}
		return date;
	}

	//修复Date.parse时区为西区的bug：如果为西区（即时区值为-），则必须要在年份后，否则会报错
	//GMT+xxxx/UT+xxxx/UTC+xxxx 及  CST
	@SuppressWarnings("deprecation")
	private static String fixDateParseZone(String sDate)
	{
		String newStr=sDate.trim();
		int iInx=0;
		int iStart=-1;
		boolean bStart=true;
		for(;iInx<newStr.length();++iInx){
			char ch=newStr.charAt(iInx);
			if(Character.isSpace(ch)){
				bStart=true;
			}else if(bStart){
				bStart=false;
				if(ch=='C' || ch=='c'){//CST
					iStart=iInx++;
					while(iInx<newStr.length()
							&& !Character.isSpace(newStr.charAt(iInx))) ++iInx;
					break;
				}else if(ch=='G' || ch=='g'){//GMT
					iStart=iInx++;
					while(iInx<newStr.length()
							&& !Character.isSpace(newStr.charAt(iInx))) ++iInx;
					break;
				}else if(ch=='U' || ch=='u'){//UT/UTC
					iStart=iInx++;
					while(iInx<newStr.length()
							&& !Character.isSpace(newStr.charAt(iInx))) ++iInx;
					break;
				}
			}
		}
		if(iStart>=0){
			String zone=newStr.substring(iStart, iInx).toUpperCase();
			if(zone.equals("CST"))
				zone="GMT+0800";//为了防止混乱，将CST替换成GMT+8
			else if(zone.indexOf(":")>=0)
				zone=zone.replace(":", "");
			newStr=newStr.substring(0,iStart)+(iInx<newStr.length()?newStr.substring(iInx):"")+" "+zone;
		}
		//System.out.println(newStr);
		return newStr;
	}

	/**
	 * 全为数字的字符串是否为日期格式：长度为4（视为yyyy），6（视为yyyyMM），8（视为yyyyMMdd）
	 * 或10（视为yyyyMMddHH），12（视为yyyyMMddHHmm），14（视为yyyyMMddHHmmss），为合法的全数字日期型字符串
	 * @param val
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static Date isDateDigit(String val)
	{
		Calendar cal=Calendar.getInstance();
		//cal.set(Calendar.YEAR, 0);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND,0);

		int ilen=val.length();
		if(ilen==4){
			cal.set(Calendar.YEAR, Integer.parseInt(val));
			return cal.getTime();
		}
		else if(ilen==6){//年月，或时分秒
			//月份值
			int iv=Integer.parseInt(val.substring(4, 6));
			if(iv==0 || iv>12) return null;
			cal.set(Calendar.YEAR, Integer.parseInt(val.substring(0, 4)));
			cal.set(Calendar.MONTH, iv-1);
			return cal.getTime();
		}
		else if(ilen==8 || ilen==10 || ilen==12 || ilen==14){
			//月份值
			cal.set(Calendar.YEAR, Integer.parseInt(val.substring(0, 4)));
			int iv=Integer.parseInt(val.substring(4, 6));
			if(iv==0 || iv>12) return null;
			cal.set(Calendar.MONTH, iv-1);
			//月内的日期（并不考虑是否合法值，比如2月没有31号）
			iv=Integer.parseInt(val.substring(6, 8));
			if(iv==0 || iv>cal.getActualMaximum(Calendar.DATE)) return null;
			cal.set(Calendar.DATE, iv);//会自动修正到正确的月日
			if(ilen>8){
				//时
				iv=Integer.parseInt(val.substring(8, 10));
				if(iv>24) return null;
				cal.set(Calendar.HOUR_OF_DAY, iv);
			}
			if(ilen>10){
				//分
				iv=Integer.parseInt(val.substring(10, 12));
				if(iv>59) return null;
				cal.set(Calendar.MINUTE, iv);
			}
			if(ilen>12){
				//秒
				iv=Integer.parseInt(val.substring(12, 14));
				if(iv>59) return null;
				cal.set(Calendar.SECOND, iv);
			}
			return cal.getTime();
		}
		return null;
	}

	/** 将标准时间转换成本地时间（中国时区+8小时）
	 * @param dtcur
	 * @return
	 */
	public static Date dateIso2Local(Date dtcur)
	{
		if(dtcur==null) return null;
		Date dtLocal=new Date(dtcur.getTime()+8*60*60*1000);
		return dtLocal;
	}
	/**
	 * 将本地时间转换成标准时间（中国时区-8小时）
	 * @param dtcur
	 * @return
	 */
	public static Date dateLocal2Iso(Date dtcur)
	{
		if(dtcur==null) return null;
		Date dtIso=new Date(dtcur.getTime()-8*60*60*1000);
		return dtIso;
	}

	/**
	 * 将Date对象转换成Calendar对象。如果date为null，则返回当前时间
	 * @param date
	 * @return
	 */
	public static Calendar toCalendar(Date date)
	{
		Calendar cal=Calendar.getInstance();
		if(date!=null)
			cal.setTime(date);
		return cal;
	}

	/** 将日期串转换成标准的格式。如果转换失败，返回Empty<br>
	 * 注意：本方法对于长度为4、6、大于等于8的全数字字符串视为yyyy、yyyyMM、yyyyMMdd及yyyyMMddH..的日期串、<br>
	 * 除此之外长度的全数字字符串返回空。<br>
	 * 以及形如：Mon May 22 21:58:42 CST 2017 或 Mon May 22 2017 21:58:42 GMT+0800 的国标格式的日期，
	 * 格式化将会不正确。<br>
	 *
	 * @param strDate 日期字符串支持格式：<br>
	 * yyyy/MM/dd HH:mm:ss、yyyy-MM-dd HH:mm:ss，或其日期部分，或时间部分<br>
	 * HH.mm.ss，<br>
	 * yyyyMMddHHmmss、yyyyMMddTHHmmss，
	 * yyyy，yyyyMM，yyyyMMdd，yyyyMMddHH，yyyyMMddHHmm<br>
	 * yyyy年MM月dd日HH时mm分ss秒、HH点mm分ss秒。
	 * 其中yyyy、MM、dd、HH、mm、ss也可以是全角数字，或汉字小写数字（如：二〇〇六）；
	 * HH也可以是12小时制数据，此时日期串需要包含AM/PM或上午/下午（如果不包含默认为上午日期）
	 *
	 * @return 标准的日期格式字符串：yyyy-MM-dd HH:mm:ss
	 */
	@SuppressWarnings("deprecation")
	public static String formatDateString(String sDateVal)
	{
		if (sDateVal == null)
			return "";
		String sR = sDateVal.trim();
		if (sR.length() == 0)
			return "";
		try
		{
			sR = sR.toUpperCase().replace('\t', ' ');
			int iTemp = 0;
			//清除末尾的时区信息
//			if ((iTemp = sR.indexOf('Z')) >= 0)
//			{//ZE8 或 Z+08:00
//				sR = sR.substring(0, iTemp);
//			}
//			else
			if ((iTemp = sR.indexOf('G')) >= 0)
			{//GMT+08:00
				sR = sR.substring(0, iTemp);
			}
			else if((iTemp=sR.indexOf('C'))>=0)
			{//CST
				sR = sR.substring(0, iTemp);
			}
			else if((iTemp=sR.indexOf('U'))>=0)
			{//UT+0800 UTC+0800
				sR = sR.substring(0, iTemp);
			}
			else if ((iTemp = sR.indexOf('+')) >= 0)
			{//+08:00
				sR = sR.substring(0, iTemp);
			}
			//上午/下午的自定义名字
			SimpleDateFormat sdf=new SimpleDateFormat("a");
			Calendar dtTest = Calendar.getInstance();
			dtTest.set(Calendar.HOUR_OF_DAY, 8);
			String sAMName = sdf.format(dtTest.getTime());
			dtTest.set(Calendar.HOUR_OF_DAY, 22);
			String sPMName = sdf.format(dtTest.getTime());
			//格式化日期部分
			if (sR.indexOf('／') >= 0)
			{
				sR = sR.replace('／', '-');
			}
			else if (sR.indexOf('－') >= 0)
			{
				sR = sR.replace('－', '-');
			}
			else if (sR.indexOf('/') >= 0)
			{
				sR = sR.replace('/', '-');
			}
			else if (sR.indexOf('年') >= 0)
			{
				sR = sR.replace('年', '-').replace('月', '-').replace('日', ' ');
			}
			//格式化时间部分
			if (sR.indexOf('：') >= 0)
			{
				sR = sR.replace('：', ':');
			}
			else if (sR.indexOf('.') >= 0)
			{
				sR = sR.replace('.', ':');
			}
			else if (sR.indexOf('分') >= 0)
			{
				sR = sR.replace('时', ':').replace('点', ':').replace('分', ':').replace('秒', ' ');
			}
			//替换“上午”字符串
			if (sR.indexOf("上午") >= 0)
			{
				sR = sR.replace("上午", " ");
			}
			else if (sR.indexOf("AM") >= 0)
			{
				sR = sR.replace("AM", " ");
			}
			else if (sR.indexOf(sAMName) >= 0)
			{
				sR = sR.replace(sAMName, " ");
			}
			if (sR.indexOf('T') >= 0)
			{
				sR = sR.replace('T', ' ');
			}
			//替换“下午”字符串
			boolean bPM = (sR.indexOf("下午") >= 0);
			if (bPM)
			{
				sR = sR.replace("下午", " ");
			}
			if (!bPM)
			{
				bPM = (sR.indexOf("PM") >= 0);
				if (bPM)
				{
					sR = sR.replace("PM", " ");
				}
			}
			if (!bPM)
			{
				bPM = (sR.indexOf(sPMName) >= 0);
				if (bPM)
				{
					sR = sR.replace(sPMName, " ");
				}
			}

			sR = sR.replace('０', '0').replace('１', '1').replace('２', '2').replace('３', '3')
				.replace('４', '4').replace('５', '5').replace('６', '6').replace('７', '7')
				.replace('８', '8').replace('９', '9');
			//〇一二三四五六七八九
			//十 一十 二十 三十 四十 五十 六十 十一 一十一 二十一 ...
			//-->十前面不是一至九，加"一"；十后面不是〇至九，加"〇"；最后将十替换成""
			iTemp = 0;
			sR = sR.replace('○', '〇');//将\xA1F0替换成\xA996
			while ((iTemp = sR.indexOf('十', iTemp)) >= 0)
			{
				if (iTemp + 1 == sR.length())
				{
					sR += "〇";
					break;
				}
				if (!CheckUtil.isCNumber(sR.charAt(iTemp + 1)))
				{
					sR = sR.substring(0, iTemp + 1)+"〇"+sR.substring(iTemp + 1);
				}

				if (iTemp == 0)
				{
					sR = "一"+sR;
					++iTemp;
				}
				else if (sR.charAt(iTemp - 1) == '〇')
				{
					sR = sR.substring(0, iTemp - 1)+"一"+sR.substring(iTemp);
				}
				else if (!CheckUtil.isCNumber(sR.charAt(iTemp - 1)))
				{
					sR = sR.substring(0, iTemp)+"一"+sR.substring(iTemp);
				}
				++iTemp;
			}
			sR = sR.replace("十", "");
			//廿 廿一 ...
			//-->廿后面不是〇至九，加"〇"；最后将廿替换成"二"
			iTemp = 0;
			while ((iTemp = sR.indexOf('廿', iTemp)) >= 0)
			{
				if (iTemp + 1 == sR.length())
				{
					sR += "〇";
					break;
				}
				if (!CheckUtil.isCNumber(sR.charAt(iTemp + 1)))
				{
					sR = (sR.substring(0, iTemp + 1)+"〇"+sR.substring(iTemp + 1));
				}
				++iTemp;
			}
			sR = sR.replace('廿', '二');
			//卅 卅一
			//-->卅后面不是〇至九，加"〇"；最后将卅替换成"三"
			iTemp = 0;
			while ((iTemp = sR.indexOf('卅', iTemp)) >= 0)
			{
				if (iTemp + 1 == sR.length())
				{
					sR += "〇";
					break;
				}
				if (!CheckUtil.isCNumber(sR.charAt(iTemp + 1)))
				{
					sR = (sR.substring(0, iTemp + 1)+"〇"+sR.substring(iTemp + 1));
				}
				++iTemp;
			}
			sR = sR.replace('卅', '三');

			sR = sR.replace('〇', '0').replace('一', '1').replace('二', '2')
					.replace('三', '3').replace('四', '4').replace('五', '5')
					.replace('六', '6').replace('七', '7').replace('八', '8')
					.replace('九', '9');
			//去除结尾可能出现的‘-’ 或 ‘:’
			iTemp=sR.length()-1;
			while(iTemp>=0){
				char chx=sR.charAt(iTemp);
				if(!Character.isSpace(chx) && chx!='-' && chx!=':')
					break;
				--iTemp;
			}
			++iTemp;
			sR=sR.substring(0, iTemp);

			int iHr = sR.indexOf(':');//按小时分隔符切分
			String sPrefix = "", sSuffix = "";
			if (sR.indexOf('-') < 0 && iHr < 0)
			{
				//是yyyymmddHHMISS格式
				sR = sR.replace(" ", "");
				if (sR.length() == 4)//yyyy
				{
					sPrefix = sR + "-01-01";
				}
				else if(sR.length() == 6){//yyyymm
					sPrefix = sR.substring(0, 4)+"-"+sR.substring(4, 6)+"-01";
				}
				else if (sR.length() >= 8)//yyyymmdd 或 yyyymmddHHMISS
				{
					sPrefix = sR.substring(0, 4)+"-"+sR.substring(4, 6)+"-"+sR.substring(6, 8);
				}
				if (sR.length() > 8)//14=yyyymmddHHMISS
				{
					int iStart = 8;
					if(sR.length()<14) //不够，表示缺时间部分，补上0
						sR=sR+ConvertUtil.RepeatrChar("0",14-sR.length(),"");
					if (bPM)
					{
						iTemp = ConvertUtil.getValue(sR.substring(iStart, iStart+2), 0);
						if (iTemp < 12)
							iTemp += 12;
						sSuffix = (Integer.toString(iTemp)+":"
							+sR.substring(iStart+2, iStart+4)+":"+sR.substring(iStart + 4, iStart+6));
					}
					else
					{
						sSuffix = (sR.substring(iStart, iStart+2)+":"+sR.substring(iStart + 2, iStart+4)
								+":"+sR.substring(iStart + 4, iStart+6));
					}
				}
				sR = sPrefix;
				if (CheckUtil.isNullorEmpty(sR))
					sR = sSuffix;
				else if (!CheckUtil.isNullorEmpty(sSuffix))
				{
					sR = (sR+" "+sSuffix);
				}
				return sR;
			}else if (sR.indexOf('-') >=0) {
				String[] sPart = sR.split("-");
				if (sPart.length == 2) {//yyyy-MM
					sR = sPart[0].replace(" ", "")+"-"+sPart[1].replace(" ", "") + "-01";
					return sR;
				}
			}

			if (iHr >= 0)
			{//有时间部分或包含日期部分
				sPrefix = sR.substring(0, iHr).trim();
				iTemp = sPrefix.lastIndexOf(' ');
				if (iTemp >= 0)
				{//包含日期部分
					sSuffix = sPrefix.substring(iTemp);
					sPrefix = sPrefix.substring(0, iTemp);
				}
				else
				{//只有时间部分
					sSuffix = sPrefix;
					sPrefix = "";
				}
				sSuffix += sR.substring(iHr);
				sSuffix = sSuffix.replace(" ", "");
				String[] saTime = sSuffix.split(":");
				if (bPM)
				{//要调整下午时间，小时加12
					iHr = ConvertUtil.getValue(saTime[0], 0);
					if (iHr < 12)
						iHr += 12;
					saTime[0] = Integer.toString(iHr);
				}
				if (saTime.length > 2)
				{
					sSuffix = (saTime[0]+":"+(saTime[1].isEmpty() ? "00" : saTime[1])
							+":"+(saTime[2].isEmpty() ? "00" : saTime[2]));
					//添加毫秒值
					//if (saTime.length() > 3)
					//  sSuffix = sSuffix + "." + saTime[3];
				}
				else if (saTime.length == 2)
				{
					sSuffix = (saTime[0]+":"+(saTime[1].isEmpty() ? "00" : saTime[1])
							+":00");
				}
				else
				{//saTime.length()==1
					sSuffix = (saTime[0]+":00:00");
				}
			}
			else
			{//只有日期部分
				sPrefix = sR;
				sSuffix = "";
			}

			sR = sPrefix.replace(" ", "");
			if (sR.isEmpty())
				sR = sSuffix;
			else if (!CheckUtil.isNullorEmpty(sSuffix))
			{
				sR = (sR+" "+sSuffix);
			}
			//删除时间后面的无效内容
			iTemp = sR.lastIndexOf(':') + 1;
			if (iTemp > 0)
			{
				//如果有毫秒值
				//int iMs = sR.lastIndexOf('.') + 1;
				//if (iMs > 0)
				//  iTemp = iMs;
				while (iTemp < sR.length() && Character.isDigit(sR.charAt(iTemp)))
					++iTemp;
				if (iTemp < sR.length())
					sR = sR.substring(0, iTemp);
			}
		}
		catch(Exception ex)
		{

			org.slf4j.Logger logOutInfo=org.slf4j.LoggerFactory.getLogger("system.out");
			logOutInfo.error("DateUtil.formatDateString error", ex);
			sR = "";
		}
		return sR;
	}
}
