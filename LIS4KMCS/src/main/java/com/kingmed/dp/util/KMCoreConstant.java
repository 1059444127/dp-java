/**  
 * Package Name:cn.myapps.util  
 * Date:2015年8月4日下午2:36:08  
 * Copyright (c) 2015, zb-huangshengbao@kingmed.com.cn All Rights Reserved.  
 *  
 */

package com.kingmed.dp.util;

/**
 * 核心系统接口常量工具类
 * 
 * @author 黄省宝 at 2015年8月4日 下午2:36:08
 * @version
 * @since JDK 1.7
 */
public class KMCoreConstant {

	public static final Integer HTTP_REQ_OK = 200;
	public static final String HTTP_CHARSET_UTF_8 = "utf-8";
	public static final String HTTP_CHARSET_ISO = "ISO8859-1";
	public static final String CONTENT_TYPE_TEXT_JSON = "text/json";
	public static final String APPLICATION_JSON = "application/json";

	/**
	 * 核心系统发送加做项目接口名称
	 */
	public static final String KM_SENDPATHOLOGYADDITEMINFO = "LB.TESTRESULT.SENDPATHOLOGYADDITEMINFO";

	/**
	 * 核心系统发送专家留言接口名称
	 */
	public static final String KM_SENDPATHOLOGYEXPERTMSGINFO = "LB.TESTRESULT.SENDPATHOLOGYEXPERTMSGINFO";

	/**
	 * 核心系统发送病理诊断意见及截图信息接口名称
	 */
	public static final String KM_SENDPATHOLOGYDIAGNOSISANDPICINFO = "LB.TESTRESULT.SENDPATHOLOGYDIAGNOSISANDPICINFO";

}
