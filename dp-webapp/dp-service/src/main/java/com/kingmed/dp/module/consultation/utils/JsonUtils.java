package com.kingmed.dp.module.consultation.utils;

import java.util.Collection;
/***
 * 
 * @author 龙庭伟
 * date:2015-8-10
 *
 */
public class JsonUtils {
	/***
	 * 集合转成json字符串
	 * @param collection
	 * @return json字符串
	 */
	public static  String collection2Json(Collection<Object> collection){
		StringBuffer sb=new StringBuffer();
		
		for (Object object : collection) {
			String str="";
			String item = object.toString().substring(1, object.toString().length()-1).replace(",","=");
			String[] items=item.split("=");
			str+="\""+items[0]+"\":\""+items[1]+"\",\""+items[2].trim()+"\":\""+items[3]+"\"";
			sb.append("{"+str+"}, ");
		}
		return "["+sb.toString().substring(0, sb.toString().trim().length()-1)+"]";
	}
}
