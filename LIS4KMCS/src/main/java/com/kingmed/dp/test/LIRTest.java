package com.kingmed.dp.test;


import com.kingmed.dp.client.KMCoreHttpService;
import com.kingmed.dp.util.JsonResult;

import net.sf.json.JSONObject;

/**
 * LIR接口测试类
 * 
 * @author 黄省宝
 * @version 1.0
 * @since JDK 1.7
 */
public class LIRTest {

	/**
	 * 接口测试方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			KMCoreHttpService test = new KMCoreHttpService();
			String url = "http://192.168.2.167:8280/services/";
			String specBarcode = "01.1_20150626_0119034139_1";
			String testNumber = "DTP0012";
			String expertMsgInfo = "专家留言测试20150805第一条留言";
			String expertId = "LIR10001";
			String expertName = "金域专家";
			String diagnosisInfo = "全面诊断";
			String picContent = "http://v1.qzone.cc/pic/201508/05/12/37/55c1931c636b0431.jpg!600x600.jpg";
			String picFileName = "0101.jpg";
			String addTestItemCode = "DT20150805";
			String addTestItemName = "DT加做项目";
			JsonResult jsonResult = null;
			test.sendDiagnosisAndPicInfo(specBarcode, testNumber, diagnosisInfo, picContent, picFileName,url);
			JSONObject jsonObject = JSONObject.fromObject(jsonResult
					.getResult());
			System.out.println("errorCode----------------"
					+ jsonObject.get("errorCode"));
			System.out.println("errorMsg================="
					+ jsonObject.get("errorMsg"));
			System.out.println("jsonResult-------------------" + jsonResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
