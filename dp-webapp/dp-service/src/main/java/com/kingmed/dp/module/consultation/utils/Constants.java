package com.kingmed.dp.module.consultation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/***
 * 
 * @author 龙庭伟 at 2015年10月22日 11：37 desc:常量
 * @version 1.0
 * @since JDK 1.6
 */
public class Constants {
	public static final String APPLICATIONID = "11e4-868e-476f6c83-afc0-bf7d9fc712b0";
	public static final String DOMAINID = "11e4-868a-e14ce859-afc0-bf7d9fc712b0";
	public static final String CPTSUFFIX = ".cpt";
	public static final String JPGSUFFIX = ".jpg";
	public static final String EXCELSUFFIX = ".xls";
	public static final String PDFSUFFIX = ".pdf";

	// 上传的相对目录
	public static final String UPLOADPATH = "uploads";

	// 发送给lir端报告单的图片的存放目录
	public static final String SENDLIRIMGPATH = "img";

	// 保存CPT文件的相对目录
	public static final String CPTSTOREPATH = "reportlets";

	// 保存EXCEL文件数据源的目录
	public static final String EXCELPATH = "datasource";

	// 所用的文件名
	public static final String NAMEPREFIX = "report";

	// 未发送状态
	public static final String UNSEND = "0";

	// 已发送状态
	public static final String SENDED = "1";
    
	//表单名称
	public final static String FORM_NAME = "金域病理远程会诊平台/未诊断/未诊断";
    
	//截图
	public final static String CI_JT = "CI_JT";
	
	//诊断状态
	public final static String CI_STATE= "CI_STATE";
	
	//患者名
	public final static String CI_NAME = "CI_NAME";
	
	//性别
	public final static String CI_GENDER = "CI_GENDER";
	
	//年龄
	public final static String CI_AGE = "CI_AGE";
	
	//病理号
	public final static String CI_TEST_ID = "CI_TEST_ID";
	
	//会诊部位
	public final static String CI_HJBW = "CI_HJBW";
	
	//临床诊断
	public final static String CI_LCZD = "CI_LCZD";
	
	//临床主诉
	public final static String CI_LCZS = "CI_LCZS";
	
	//诊断意见
	public final static String CI_ZDYJ = "CI_ZDYJ";
	
	//诊断者id
	public final static String CI_ZDZ = "CI_ZDZ";
	
	//条码号
	public final static String CI_REQUEST_CODE = "CI_REQUEST_CODE";
	
	//子公司
	public final static String CI_CHILD_COMPANY = "CI_CHILD_COMPANY";
	
    //专家签名
	public final static String SIGNER = "SIGNER";
	
	//已诊断状态
	public final static String DIAGNOSED="1";

	// 下载模板文件的目录
	public static String getCptStorePath() {
		String time = new SimpleDateFormat("yyMMdd").format(new Date())
				+ new Date().getTime();
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = Constants.class.getClassLoader().getResourceAsStream(
					"/systempath.properties");
			properties.load(inputStream);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return properties.getProperty("path") + "downloadfile\\" + time;
	}
	
	//江丰截图存放的子目录 add by ltw at 2015-11-26
	public final static String JFJTDir="ScreenShot";
	
	//报告单改变的状态
	public final static String RPCHANGESTATED="1";
	
	//报告单未改变的状态
	public final static String UNRPCHANGESTATED="0";
	
	//非病理专家标记 add by ltw at 2015-12-01
	public final static int UNPATHOLOGYFLAG=2;
	
	//诊断意见已提交状态 modify by ltw at 2015-12-02
	public final static int DIAGNOSESUBMITED=3;

}
