/**
 * Package Name:cn.myapps.client
 * Date:2015年8月4日上午11:04:06
 * Copyright (c) 2015, zb-huangshengbao@kingmed.com.cn All Rights Reserved.
 *
 */

package com.kingmed.dp.client;

import org.apache.log4j.Logger;

import com.kingmed.dp.util.ErrorCode;
import com.kingmed.dp.util.JsonResult;
import com.kingmed.dp.util.KMCoreConstant;
import com.kingmed.dp.util.Util;

/**
 * HTTP POST 方式调用核心系统接口类
 *
 * @author 黄省宝 at 2015年8月4日 下午4:15:08
 * @version 1.0
 * @since JDK 1.7
 */
public class KMCoreHttpService {

	private Logger logger = Logger.getLogger(KMCoreHttpService.class);

	/**
	 * 发送病理诊断意见及截图信息
	 *
	 * @param specBarcode
	 *            :标本条码
	 * @param testNumber
	 *            :实验号
	 * @param diagnosisInfo
	 *            :诊断意见
	 * @param picContent
	 *            :病理截图(Base64编码)，必须是JPG格式
	 * @param picFileName
	 *            :病理截图图片名称
	 * @return JsonResult
	 */
	public JsonResult sendDiagnosisAndPicInfo(String specBarcode,
			String testNumber, String diagnosisInfo, String picContent,
			String picFileName, String kmcsUrl) {

		JsonResult jsonResult = new JsonResult();
		try {
			/**
			 * 接口参数校验
			 */
			jsonResult = Util.validateDiagnosisAndPicInfo(specBarcode,
					testNumber, diagnosisInfo, picContent, picFileName);
			System.out.println(".....................................validate jsonResult " +jsonResult);
			if (jsonResult.getStatus() == 0) {// 校验通过
				/**
				 * 获取请求参数JSON数据
				 */
				String content = Util.getDiagnosisJson(specBarcode, testNumber,
						diagnosisInfo, picContent, picFileName);
				/**
				 * 调用接口方法，发送请求
				 */
				StringBuilder urlBuffer = new StringBuilder();
				urlBuffer.append(kmcsUrl).append(
						KMCoreConstant.KM_SENDPATHOLOGYDIAGNOSISANDPICINFO);
				jsonResult = Util.postData(content, urlBuffer.toString(),
						KMCoreConstant.KM_SENDPATHOLOGYDIAGNOSISANDPICINFO);
				System.out.println(".....................................postData jsonResult " +jsonResult);
			}
		} catch (Exception e) {
			System.out.println(".....................................exception ");
			jsonResult.setResult("系统异常，请稍后再试");
			jsonResult.addErrorCode(ErrorCode.SYS_ERR);
			logger.error(
					"调用KMCoreHttpService-sendDiagnosisAndPicInfo方法系统异常:" + e.getMessage(), e);
		}
		return jsonResult;
	}

	/**
	 * 发送专家留言信息
	 *
	 * @param specBarcode
	 *            :标本条码
	 * @param testNumber
	 *            :实验号
	 * @param expertMsgInfo
	 *            :专家留言信息
	 * @param expertId
	 *            :专家ID
	 * @param expertName
	 *            :专家名称
	 * @return JsonResult
	 */
	public JsonResult sendExpertMsgInfo(String specBarcode, String testNumber,
			String expertMsgInfo, String expertId, String expertName,
			String kmcsUrl) {

		JsonResult jsonResult = new JsonResult();
		try {
			/**
			 * 校验参数
			 */
			jsonResult = Util.validateExpertMsg(specBarcode, testNumber,
					expertMsgInfo);
			/**
			 * 校验成功
			 */
			if (jsonResult.getStatus() == 0) {
				/**
				 * 获取请求参数JSON数据
				 */
				String content = Util.getExpertMsgJson(specBarcode, testNumber,
						expertMsgInfo, expertId, expertName);

				/**
				 * 调用接口，发送请求
				 */
				StringBuilder urlBuffer = new StringBuilder();
				urlBuffer.append(kmcsUrl).append(
						KMCoreConstant.KM_SENDPATHOLOGYEXPERTMSGINFO);
				jsonResult = Util.postData(content, urlBuffer.toString(),
						KMCoreConstant.KM_SENDPATHOLOGYEXPERTMSGINFO);
			}
		} catch (Exception e) {
			jsonResult.setResult("系统异常，请稍后再试");
			jsonResult.addErrorCode(ErrorCode.SYS_ERR);
			logger.error(
					"调用KMCoreHttpService-sendExpertMsgInfo系统异常:" + e.getMessage(),
					e);
		}
		return jsonResult;
	}

	/**
	 * 发送加做项目信息
	 *
	 * @param specBarcode
	 *            :标本条码
	 * @param testNumber
	 *            :实验号
	 * @param addTestItemCode
	 *            :加做项目代码
	 * @param addTestItemName
	 *            :加做项目名称
	 * @return JsonResult
	 */
	public JsonResult sendAddItemInfo(String specBarcode, String testNumber,
			String addTestItemCode, String addTestItemName, String kmcsUrl) {

		JsonResult jsonResult = new JsonResult();
		try {
			/**
			 * 校验参数
			 */
			jsonResult = Util.validateAddItemInfo(specBarcode, testNumber,
					addTestItemCode);
			/**
			 * 校验成功
			 */
			if (jsonResult.getStatus() == 0) {
				/**
				 * 获取请求参数JSON数据
				 */
				String content = Util.getAddItemInfoJson(specBarcode,
						testNumber, addTestItemCode, addTestItemName);

				/**
				 * 调用接口，发送请求
				 */
				StringBuilder urlBuffer = new StringBuilder();
				urlBuffer.append(kmcsUrl).append(
						KMCoreConstant.KM_SENDPATHOLOGYADDITEMINFO);
				jsonResult = Util.postData(content, urlBuffer.toString(),
						KMCoreConstant.KM_SENDPATHOLOGYADDITEMINFO);
			}
		} catch (Exception e) {
			jsonResult.setResult("系统异常，请稍后再试");
			jsonResult.addErrorCode(ErrorCode.SYS_ERR);
			logger.error(
					"调用KMCoreHttpService-sendAddItemInfo系统异常:" + e.getMessage(), e);
		}
		return jsonResult;
	}
	
	/**
	 * Added by zhengjunjie on 20151118 
	 * 发送诊断意见给新核心系统，需要发送实验号和检测项目ID
	 * 
	 * 发送病理诊断意见及截图信息
	 *
	 * @param specBarcode
	 *            :标本条码
	 * @param testNumber
	 *            :实验号
	 * @param testItemId
	 * 			  :检测项目ID
	 * @param diagnosisInfo
	 *            :诊断意见
	 * @param picContent
	 *            :病理截图(Base64编码)，必须是JPG格式
	 * @param picFileName
	 *            :病理截图图片名称
	 * @return JsonResult
	 */
	public JsonResult sendDiagnosisAndPicInfo(String specBarcode,
											  String testNumber,
											  String testItemId,
											  String diagnosisInfo, 
											  String picContent,
											  String picFileName, 
											  String kmcsUrl
											  ) {

		JsonResult jsonResult = new JsonResult();
		try {
			// 接口参数校验
			jsonResult = Util.validateDiagnosisAndPicInfo(specBarcode, testNumber, diagnosisInfo, picContent, picFileName);
			System.out.println(".....................................validate jsonResult " +jsonResult);
			if (jsonResult.getStatus() == 0) {// 校验通过
				// 获取请求参数JSON数据
				String content = Util.getDiagnosisJson(specBarcode, testNumber, testItemId,	diagnosisInfo, picContent, picFileName);
				// 调用接口方法，发送请求
				StringBuilder urlBuffer = new StringBuilder();
				urlBuffer.append(kmcsUrl).append(KMCoreConstant.KM_SENDPATHOLOGYDIAGNOSISANDPICINFO);
				jsonResult = Util.postData(content, urlBuffer.toString(), KMCoreConstant.KM_SENDPATHOLOGYDIAGNOSISANDPICINFO);
				System.out.println(".....................................postData jsonResult " +jsonResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(".....................................exception ");
			jsonResult.setResult("发送诊断意见到新核心系统，系统异常，请稍后再试");
			jsonResult.addErrorCode(ErrorCode.SYS_ERR);
			logger.error("调用KMCoreHttpService-sendDiagnosisAndPicInfo方法系统异常:" + e.getMessage(), e);
		}
		return jsonResult;
	}

	/**
	 * Added by zhengjunjie on 20151118 
	 * 发送留言信息，需要发送实验号和检测项目ID
	 * 
	 * 发送专家留言信息
	 *
	 * @param specBarcode
	 *            :标本条码
	 * @param testNumber
	 *            :实验号
	 * @param testItemId
	 * 			  :检测项目ID
	 * @param expertMsgInfo
	 *            :专家留言信息
	 * @param expertId
	 *            :专家ID
	 * @param expertName
	 *            :专家名称

	 * @return JsonResult
	 */
	public JsonResult sendExpertMsgInfo(String specBarcode, 
										String testNumber, 
										String testItemId,
										String expertMsgInfo, 
										String expertId, 
										String expertName,
										String kmcsUrl
										) {
		JsonResult jsonResult = new JsonResult();
		try {
			// 校验参数
			jsonResult = Util.validateExpertMsg(specBarcode, testNumber, expertMsgInfo);
			// 校验成功
			if (jsonResult.getStatus() == 0) {
				// 获取请求参数JSON数据
				String content = Util.getExpertMsgJson(specBarcode, testNumber, testItemId, expertMsgInfo, expertId, expertName);
				// 调用接口，发送请求
				StringBuilder urlBuffer = new StringBuilder();
				urlBuffer.append(kmcsUrl).append(KMCoreConstant.KM_SENDPATHOLOGYEXPERTMSGINFO);
				jsonResult = Util.postData(content, urlBuffer.toString(), KMCoreConstant.KM_SENDPATHOLOGYEXPERTMSGINFO);
			}
		} catch (Exception e) {
			jsonResult.setResult("专家留言，系统异常，请稍后再试");
			jsonResult.addErrorCode(ErrorCode.SYS_ERR);
			logger.error("调用KMCoreHttpService-sendExpertMsgInfo系统异常:" + e.getMessage(), e);
		}
		return jsonResult;
	}

	/**
	 * Added by zhengjunjie on 20151118 
	 * 加做项目，需要发送实验号和检测项目ID
	 * 发送加做项目信息
	 *
	 * @param specBarcode
	 *            :标本条码
	 * @param testNumber
	 *            :实验号
	 * @param testItemId
	 * 			  :检测项目ID
	 * @param addTestItemCode
	 *            :加做项目代码
	 * @param addTestItemName
	 *            :加做项目名称
	 * @return JsonResult
	 */
	public JsonResult sendAddItemInfo(String specBarcode, 
									  String testNumber,
									  String testItemId,
									  String addTestItemCode, 
									  String addTestItemName, 
									  String kmcsUrl) {

		JsonResult jsonResult = new JsonResult();
		try {
			// 校验参数
			jsonResult = Util.validateAddItemInfo(specBarcode, testNumber, addTestItemCode);
			// 校验参数
			if (jsonResult.getStatus() == 0) {
				// 获取请求参数JSON数据
				String content = Util.getAddItemInfoJson(specBarcode, testNumber, testItemId, addTestItemCode, addTestItemName);
				/**
				 * 调用接口，发送请求
				 */
				StringBuilder urlBuffer = new StringBuilder();
				urlBuffer.append(kmcsUrl).append( KMCoreConstant.KM_SENDPATHOLOGYADDITEMINFO);
				jsonResult = Util.postData(content, urlBuffer.toString(), KMCoreConstant.KM_SENDPATHOLOGYADDITEMINFO);
			}
		} catch (Exception e) {
			jsonResult.setResult("加做项目，系统异常，请稍后再试");
			jsonResult.addErrorCode(ErrorCode.SYS_ERR);
			logger.error("调用KMCoreHttpService-sendAddItemInfo系统异常:" + e.getMessage(), e);
		}
		return jsonResult;
	}

}
