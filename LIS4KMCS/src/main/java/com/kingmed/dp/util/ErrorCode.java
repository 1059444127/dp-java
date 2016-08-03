package com.kingmed.dp.util;

import net.sf.json.JSONObject;

/**
 * 错误码定义 date: 2015年8月4日 上午11:14:54
 * 
 * @author 黄省宝
 * @version 1.0
 * @since JDK 1.7
 */
public class ErrorCode {

	public static final ErrorCode SYS_ERR = new ErrorCode(100001, "系统内部错误！");
	public static final ErrorCode LESS_PARAM = new ErrorCode(100002, "缺少参数错误！");
	public static final ErrorCode KMCS_SYS_ERR = new ErrorCode(100003,
			"调用核心系统接口系统错误！");

	/**
	 * 标本条码为空
	 */
	public static final ErrorCode LESS_SPECBARCODE = new ErrorCode(100004,
			"specBarcode:标本条码为空");
	/**
	 * 实验号为空
	 */
	public static final ErrorCode LESS_TESTNUMBER = new ErrorCode(100005,
			"testNumber:实验号为空");
	/**
	 * 诊断意见为空
	 */
	public static final ErrorCode LESS_DIAGNOSISINFO = new ErrorCode(100006,
			"diagnosisInfo:诊断意见为空");
	/**
	 * 病理截图为空
	 */
	public static final ErrorCode LESS_PICCONTENT = new ErrorCode(100007,
			"picContent:病理截图为空");
	/**
	 * 病理截图图片名称为空
	 */
	public static final ErrorCode LESS_PICFILENAME = new ErrorCode(100008,
			"picFileName:病理截图图片名称为空");
	/**
	 * 专家留言信息为空
	 */
	public static final ErrorCode LESS_EXPERTMSGINFO = new ErrorCode(100009,
			"expertMsgInfo:专家留言信息为空");
	/**
	 * 加做项目代码为空
	 */
	public static final ErrorCode LESS_ADDTESTITEMCODE = new ErrorCode(1000010,
			"addTestItemCode:加做项目代码为空");

	/**
	 * POST请求参数
	 */
	public static final ErrorCode LESS_POST_URL = new ErrorCode(1000012,
			"请求地址psotUrl为空");
	public static final ErrorCode LESS_POST_CONTENT = new ErrorCode(1000011,
			"请求内容content为空");
	public static final ErrorCode LESS_POST_SERVICECODE = new ErrorCode(
			1000013, "请求方法名称targetServiceCode为空");

	private int code;
	private String msg;

	public ErrorCode() {
	}

	public ErrorCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String toJson() {
		return JSONObject.fromObject(this).toString();
	}
}
