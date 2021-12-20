package com.qitai.utils;

//import java.io.Reader;

import com.qitai.utils.exception.ExceptionUtil;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator 数据查询结果处理
 */
public class ResultSetUtil {
	/**
	 * 获取数据列表
	 * @param rs 数据结果
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getRowDatas(ResultSet rs) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
		int columnCount = md.getColumnCount();
		//Reader inStream=null;
		while (rs.next()) {
			Map<String, Object> rowData = new LinkedHashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i),fetchLob(rs.getObject(i)));
				/*
				int t=md.getColumnType(i);
				switch (t) {
				case Types.CLOB:
					if(rs.getObject(i)==null){
						rowData.put(md.getColumnName(i),""); 
						break;
					}
					Clob clob=(Clob)(rs.getObject(i));
					inStream = clob.getCharacterStream();
					  char[] c = new char[(int) clob.length()];
					  inStream.read(c);
					  //data是读出并需要返回的数据，类型是String
					  rowData.put(md.getColumnName(i), new String(c)); 
					  break;
				case Types.NCLOB:
					if(rs.getObject(i)==null){
						rowData.put(md.getColumnName(i),""); 
						break;
					}
					NClob nclob=(NClob)(rs.getObject(i));
					inStream = nclob.getCharacterStream();
					  char[] nc = new char[(int) nclob.length()];
					  inStream.read(nc);
					  //data是读出并需要返回的数据，类型是String
					  rowData.put(md.getColumnName(i), new String(nc)); 
					break;
				case Types.BLOB:
					if(rs.getObject(i)==null){
						rowData.put(md.getColumnName(i),null); 
						break;
					}
					Blob blob=(Blob)(rs.getObject(i));
					rowData.put(md.getColumnName(i), blob.getBytes(1, (int)blob.length())); 
					break;
				default:
					rowData.put(md.getColumnName(i), rs.getObject(i));
					break;
				}*/
			}
			list.add(rowData);
		}
		return list;
	}
	
	/**
	 * 提取lob类值的值。
	 * @param value 要转换的原始值
	 * @return 转换后的值：
	 * java.sql.CLOB或java.sql.NCLOB转换为string；
	 * java.sql.BLOB转换为byte[]；
	 * 其他类型的值原样返回。
	 */
	public static Object fetchLob(Object value)
	{
		Object result = null;
		try
		{
			if (value instanceof Clob)
			{
				Clob clob = (Clob)value;
				result = clob.getSubString((long)1, (int)clob.length());
			}
			else if(value instanceof NClob){
				NClob clob = (NClob)value;
				result = clob.getSubString((long)1, (int)clob.length());
			}
			else if(value instanceof Blob){
				Blob blob = (Blob)value;
				result = blob.getBytes(1,(int)blob.length());
			}
			else
			{
				result = value;
			}
		}
		catch(Exception e)
		{
			ExceptionUtil.log(e);
			e.printStackTrace();
		}
		return result;
	}

}
