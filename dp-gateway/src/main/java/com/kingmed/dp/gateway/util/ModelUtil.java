package com.kingmed.dp.gateway.util;

import java.util.HashMap;
import java.util.Map;

import com.kingmed.dp.gateway.dto.SlideDto;
import com.kingmed.dp.servlet.modules.Gateway;

public class ModelUtil {

	//public final static String LOGIN_DATA = "username=admin&password=123456";
	public final static String dp_url = Gateway.gateways("dp_url");
	
	public final static String lisUrl = Gateway.gateways("lisUrl");
	
	public final static String ALL_USERS_URL = Gateway.gateways("all_users_url");
	
	public final static String NODEJS_PORT = Gateway.gateways("nodejs_port");
	
	public final static String IS_TO_SEND = Gateway.gateways("is_to_send");
	
	public final static String DP_USERNAME = Gateway.gateways("dp_username");
	public final static String DP_PASSWORD = Gateway.gateways("dp_password");
	public final static String POINT_USERNAME = Gateway.gateways("point_username");
	public final static String POINT_PASSWORD = Gateway.gateways("point_password");
	
	public static Map<String,Object> getDPLoginData(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", DP_USERNAME);
		map.put("password", DP_PASSWORD);
		return map;
	}
	
	public static Map<String,Object> getpointLoginData(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", POINT_USERNAME);
		map.put("password", POINT_PASSWORD);
		return map;
	}
	/**
	 * 会诊-病例
	 * @param mapJson
	 * @return
	 */
	public static Map<String,Object> getCstCase(Map<String, Object> mapJson){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("caseLib.caseId", mapJson.get("ci_test_id"));
		map.put("requestId", mapJson.get("ci_request_id"));
		map.put("kmbarcode", mapJson.get("ci_request_code"));
		map.put("testCode", mapJson.get("ci_itemcode"));
		map.put("name", mapJson.get("ci_name"));
		map.put("gender", mapJson.get("ci_gender"));
		map.put("age", mapJson.get("ci_age"));
		map.put("ageUnit", mapJson.get("ci_unit"));
		map.put("cliDia", mapJson.get("ci_lczd"));
		map.put("cliMas", mapJson.get("ci_lczs"));
		map.put("famHis", mapJson.get("ci_jts"));
		map.put("othHis", mapJson.get("ci_qtbs"));
		map.put("othSup", mapJson.get("ci_qtfzjc"));
		map.put("opeOrg", mapJson.get("ci_ssbw"));
		map.put("opeFind", mapJson.get("ci_sssj"));
		map.put("priPatDia", mapJson.get("ci_czdblzd"));
		map.put("cstPatExp", mapJson.get("ci_zdz"));
		map.put("applyDate", mapJson.get("ci_sqrq"));//申请日期
		map.put("bioOrg", mapJson.get("ci_hjbw"));
		map.put("company.id", mapJson.get("ci_child_company"));
		map.put("state", mapJson.get("ci_state"));
		map.put("srcApp", mapJson.get("ci_src_app"));
		map.put("srcEnv", mapJson.get("ci_env"));
		return map;
	}
	/**
	 * 会诊-反馈信息
	 * @param mapJson
	 * @param test_id
	 * @return
	 */
	public static Map<String,Object> getCstAdvice(Map<String, Object> mapJson,String test_id){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("caseId", test_id);
		map.put("content", mapJson.get("lea_msg"));
		map.put("srcApp", mapJson.get("lea_type"));//发送系统
		return map;
	}
	/**
	 * 会诊-数字切片
	 * @param mapJson
	 * @param test_id
	 * @return
	 */
	public static Map<String,Object> getslide(Map<String, Object> mapJson,String test_id){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("caseId", test_id);
		map.put("requestId", mapJson.get("ns_request_id"));
		map.put("kmbarcode", mapJson.get("ns_request_code"));
		map.put("barcode", mapJson.get("ns_name"));
		map.put("url", mapJson.get("ns_url"));
		map.put("scanner", mapJson.get("ns_vender"));
		map.put("antitbodyName", mapJson.get("ns_antibody"));
		map.put("antitbodyCode", mapJson.get("ns_antibody"));
		map.put("overview", mapJson.get("ns_img_url"));
		return map;
	}
	/**
	 * 数字切片上传
	 * @param slide
	 * @return
	 */
	public static Map<String,Object> getSlideMap(SlideDto slide){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("barcode", slide.getBarcode() );
	    map.put("label", slide.getLabel() );
	    map.put("overview", slide.getOverview() );
	    map.put("labelWithOverview", slide.getLabelWithOverview() );
	    map.put("url", slide.getUrl() );
	    map.put("scannerModel", slide.getScannerModel() );
	    map.put("createDateTime", slide.getCreateDateTime() );
	    map.put("scannerCode", slide.getScannerCode() );
	    map.put("physicalPath", slide.getPhysicalPath() );
	    map.put("path", slide.getPath() );
	    map.put("token", slide.getToken() );
	    map.put("point", slide.getPoint() );
		return map;
	}
	/**
	 * 数字切片上传时，拼接的url,传递的参数
	 * @param slide
	 * @return
	 */
	public static Map<String,Object> getUrlParamData(SlideDto slide){
		String url = lisUrl+slide.getPath()+"/"+slide.getBarcode()+"?"+slide.getToken();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("url", url);
		map.put("point", slide.getPoint());
		return map;
	}
	
}
