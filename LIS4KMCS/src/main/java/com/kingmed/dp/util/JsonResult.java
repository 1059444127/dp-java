package com.kingmed.dp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONObject;

/**
 * 处理JSON参数工具类 2015年8月4日 上午10:55:23
 * 
 * @author 黄省宝
 * @version 1.0
 * @since JDK 1.7
 */
public class JsonResult {
	private static final int SUCCESS = 0;
	private static final int FAILED = 1;

	private int status = SUCCESS;
	private List<ErrorCode> errors = new ArrayList<ErrorCode>();
	private Object result;
	private Map<String, Object> extra = new TreeMap<String, Object>();

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<ErrorCode> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorCode> errors) {
		this.errors = errors;
	}

	public void addErrorCode(ErrorCode errorCode) {
		status = FAILED;
		errors.add(errorCode);
	}

	public boolean validResult() {
		return status == SUCCESS;
	}

	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	public Map<String, Object> getExtra() {
		return extra;
	}

	public void setExtra(Map<String, Object> extra) {
		this.extra = extra;
	}

}
