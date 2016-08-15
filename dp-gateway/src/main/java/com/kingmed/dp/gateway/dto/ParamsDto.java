package com.kingmed.dp.gateway.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ParamsDto implements Serializable{

	private static final long serialVersionUID = -8627872998073118758L;

	public static final String TESTID = "testId";
	public static final String BARCODE = "barcode";
	public static final String ANTIBODY = "antiBody";
	
	private Map<String,String> map;

	public ParamsDto() {
		super();
	}
	public ParamsDto(HttpServletRequest request,String[] keys) {
		map = new HashMap<String,String>();
		for(String key : keys){
			map.put(key, request.getParameter(key));
		}
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
}
