package cn.myapps.test;


import cn.myapps.client.LIRService;

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
			LIRService lirService = new LIRService();
			
			String aSiteCode = "1";
			String aRequestCode = "201501010";
			String aTestNum = "20151010";
			String aContent = "20151010测试";
			String address = "http://localhost:8080/obpm/services/DocumentService";
			lirService.sendPathDiagnosisinfo(aSiteCode, aRequestCode, aTestNum, aContent, address);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
