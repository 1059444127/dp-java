package com.kingmed.dp.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.axis.encoding.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

/**
 * LIR接口工具类
 * 
 * @author 黄省宝 at 2015年8月4日 上午9:22:24
 * @version 1.0
 * @since JDK 1.7
 */
public class Util {

	private static Logger logger = Logger.getLogger(Util.class);

	/**
	 * 发送病理诊断意见及截图信息请求JSON报文
	 * 
	 * @param specBarcode
	 *            :标本条码
	 * @param testNumber
	 *            :实验号
	 * @param diagnosisInfo
	 *            :诊断意见
	 * @param picContent
	 *            :病理截图(Base64编码)
	 * @param picFileName
	 *            :病理截图图片名称
	 * @return jsonInfo
	 */
	public static String getDiagnosisJson(String specBarcode,
			String testNumber, String diagnosisInfo, String picContent,
			String picFileName) throws Exception {

		String jsonInfo = "";
		StringBuilder jsonBuffer = new StringBuilder();
		jsonBuffer.append("{");
		jsonBuffer.append("specBarcode:'").append(specBarcode).append("',");
		jsonBuffer.append("testNumber:'").append(testNumber).append("',");
		jsonBuffer.append("diagnosisInfo:'").append(diagnosisInfo).append("',");
		/**
		 * 转换Base64编码格式
		 */
		String picToBase64 = imgToBase64String(picContent);
		jsonBuffer.append("picContent:'").append(picToBase64).append("',");
		jsonBuffer.append("picFileName:'").append(picFileName).append("'");
		jsonBuffer.append("}");
		jsonInfo = jsonBuffer.toString();
		return JSONObject.fromObject(jsonInfo).toString();
	}
	
	/**
	 * Added by zhengjunjie on 20151118 
	 * 发送诊断意见给新核心系统，需要发送试验号和检测项目ID
	 * 发送病理诊断意见及截图信息请求JSON报文
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
	 *            :病理截图(Base64编码)
	 * @param picFileName
	 *            :病理截图图片名称
	 * @return jsonInfo
	 */
	public static String getDiagnosisJson(String specBarcode,
			String testNumber, String testItemId, String diagnosisInfo, String picContent,
			String picFileName) throws Exception {

		String jsonInfo = "";
		StringBuilder jsonBuffer = new StringBuilder();
		jsonBuffer.append("{");
		jsonBuffer.append("specBarcode:'").append(specBarcode).append("',");
		jsonBuffer.append("testNumber:'").append(testNumber).append("',");
		jsonBuffer.append("testItemId:'").append(testItemId).append("',");
		jsonBuffer.append("diagnosisInfo:'").append(diagnosisInfo).append("',");
		/**
		 * 转换Base64编码格式
		 */
		String picToBase64 = imgToBase64String(picContent);
		jsonBuffer.append("picContent:'").append(picToBase64).append("',");
		jsonBuffer.append("picFileName:'").append(picFileName).append("'");
		jsonBuffer.append("}");
		jsonInfo = jsonBuffer.toString();
		return JSONObject.fromObject(jsonInfo).toString();
	}

	/**
	 * 校验发送病理诊断意见及截图信息参数是否为空
	 * 
	 * @param specBarcode
	 *            :标本条码
	 * @param testNumber
	 *            :实验号
	 * @param diagnosisInfo
	 *            :诊断意见
	 * @param picContent
	 *            :病理截图(Base64编码)
	 * @param picFileName
	 *            :病理截图图片名称
	 * @return JsonResult
	 */
	public static JsonResult validateDiagnosisAndPicInfo(String specBarcode,
			String testNumber, String diagnosisInfo, String picContent,
			String picFileName) {

		JsonResult jsonResult = new JsonResult();
		if (StringUtils.isEmpty(specBarcode)) {
			jsonResult.addErrorCode(ErrorCode.LESS_SPECBARCODE);
			return jsonResult;
		} else if (StringUtils.isEmpty(testNumber)) {
			jsonResult.addErrorCode(ErrorCode.LESS_TESTNUMBER);
			return jsonResult;
		} else if (StringUtils.isEmpty(diagnosisInfo)) {
			jsonResult.addErrorCode(ErrorCode.LESS_DIAGNOSISINFO);
			return jsonResult;
		} else if (StringUtils.isEmpty(picContent)) {
			jsonResult.addErrorCode(ErrorCode.LESS_PICCONTENT);
			return jsonResult;
		} else if (StringUtils.isEmpty(picFileName)) {
			jsonResult.addErrorCode(ErrorCode.LESS_PICFILENAME);
			return jsonResult;
		} else {
			jsonResult.setResult("success");
		}
		return jsonResult;
	}

	/**
	 * 发送专家留言请求JSON报文
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
	 * @return jsonInfo
	 */
	public static String getExpertMsgJson(String specBarcode,
			String testNumber, String expertMsgInfo, String expertId,
			String expertName) {

		String jsonInfo = "";
		StringBuilder jsonBuffer = new StringBuilder();
		jsonBuffer.append("{");
		jsonBuffer.append("specBarcode:'").append(specBarcode).append("',");
		jsonBuffer.append("testNumber:'").append(testNumber).append("',");
		jsonBuffer.append("expertMsgInfo:'").append(expertMsgInfo).append("',");
		jsonBuffer.append("expertId:'").append(expertId).append("',");
		jsonBuffer.append("expertName:'").append(expertName).append("'");
		jsonBuffer.append("}");
		jsonInfo = jsonBuffer.toString();
		return JSONObject.fromObject(jsonInfo).toString();
	}
	
	/**
	 * Added by zhengjunjie on 20151118 
	 * 发送留言信息，需要发送实验号和检测项目ID
	 * 发送专家留言请求JSON报文
	 * 
	 * @param specBarcode
	 *            :标本条码
	 * @param testNumber
	 *            :实验号
	 * @param testItemId
	 *            :检测项目Id
	 * @param expertMsgInfo
	 *            :专家留言信息
	 * @param expertId
	 *            :专家ID
	 * @param expertName
	 *            :专家名称
	 * @return jsonInfo
	 */
	public static String getExpertMsgJson(String specBarcode,
			String testNumber, String testItemId, String expertMsgInfo, String expertId,
			String expertName) {

		String jsonInfo = "";
		StringBuilder jsonBuffer = new StringBuilder();
		jsonBuffer.append("{");
		jsonBuffer.append("specBarcode:'").append(specBarcode).append("',");
		jsonBuffer.append("testNumber:'").append(testNumber).append("',");
		jsonBuffer.append("testItemId:'").append(testItemId).append("',");
		jsonBuffer.append("expertMsgInfo:'").append(expertMsgInfo).append("',");
		jsonBuffer.append("expertId:'").append(expertId).append("',");
		jsonBuffer.append("expertName:'").append(expertName).append("'");
		jsonBuffer.append("}");
		jsonInfo = jsonBuffer.toString();
		return JSONObject.fromObject(jsonInfo).toString();
	}

	/**
	 * 校验专家留言参数是否为空
	 * 
	 * @param specBarcode
	 *            :标本条码
	 * @param testNumber
	 *            :实验号
	 * @param expertMsgInfo
	 *            :专家留言信息
	 * @return JsonResult
	 */
	public static JsonResult validateExpertMsg(String specBarcode,
			String testNumber, String expertMsgInfo) {

		JsonResult jsonResult = new JsonResult();
		if (StringUtils.isEmpty(specBarcode)) {
			jsonResult.addErrorCode(ErrorCode.LESS_SPECBARCODE);
		} else if (StringUtils.isEmpty(testNumber)) {
			jsonResult.addErrorCode(ErrorCode.LESS_TESTNUMBER);
		} else if (StringUtils.isEmpty(expertMsgInfo)) {
			jsonResult.addErrorCode(ErrorCode.LESS_EXPERTMSGINFO);
		} else {
			jsonResult.setResult("success");
		}
		return jsonResult;
	}

	/**
	 * 发送加做项目请求JSON报文
	 * 
	 * @param specBarcode
	 *            :标本条码
	 * @param testNumber
	 *            :实验号
	 * @param addTestItemCode
	 *            :加做项目代码
	 * @param addTestItemName
	 *            :加做项目名称
	 * @return jsonInfo
	 */
	public static String getAddItemInfoJson(String specBarcode,
			String testNumber, String addTestItemCode, String addTestItemName) {

		String jsonInfo = "";
		StringBuilder jsonBuffer = new StringBuilder();
		jsonBuffer.append("{");
		jsonBuffer.append("specBarcode:'").append(specBarcode).append("',");
		jsonBuffer.append("testNumber:'").append(testNumber).append("',");
		jsonBuffer.append("addTestItemCode:'").append(addTestItemCode)
				.append("',");
		jsonBuffer.append("addTestItemName:'").append(addTestItemName)
				.append("'");
		jsonBuffer.append("}");
		jsonInfo = jsonBuffer.toString();
		return JSONObject.fromObject(jsonInfo).toString();
	}
	
	/**
	 * Added by zhengjunjie on 20151118 
	 * 发送加做项目请求，需要发送试验号和检测项目ID
	 * 
	 * 发送加做项目请求JSON报文
	 * 
	 * @param specBarcode
	 *            :标本条码
	 * @param testNumber
	 *            :实验号
	 * @param addTestItemCode
	 *            :加做项目代码
	 * @param addTestItemName
	 *            :加做项目名称
	 * @return jsonInfo
	 */
	public static String getAddItemInfoJson(String specBarcode, String testNumber, String testItemId, String addTestItemCode, String addTestItemName) {

		String jsonInfo = "";
		StringBuilder jsonBuffer = new StringBuilder();
		jsonBuffer.append("{");
		jsonBuffer.append("specBarcode:'").append(specBarcode).append("',");
		jsonBuffer.append("testNumber:'").append(testNumber).append("',");
		jsonBuffer.append("testItemId:'").append(testItemId).append("',");
		jsonBuffer.append("addTestItemCode:'").append(addTestItemCode).append("',");
		jsonBuffer.append("addTestItemName:'").append(addTestItemName).append("'");
		jsonBuffer.append("}");
		jsonInfo = jsonBuffer.toString();
		return JSONObject.fromObject(jsonInfo).toString();
	}

	/**
	 * 
	 * @param specBarcode
	 *            :标本条码
	 * @param testNumber
	 *            :实验号
	 * @param addTestItemCode
	 *            :加做项目代码
	 * @return JsonResult
	 */
	public static JsonResult validateAddItemInfo(String specBarcode,
			String testNumber, String addTestItemCode) {

		JsonResult jsonResult = new JsonResult();
		if (StringUtils.isEmpty(specBarcode)) {
			jsonResult.addErrorCode(ErrorCode.LESS_SPECBARCODE);
		} else if (StringUtils.isEmpty(testNumber)) {
			jsonResult.addErrorCode(ErrorCode.LESS_TESTNUMBER);
		} else if (StringUtils.isEmpty(addTestItemCode)) {
			jsonResult.addErrorCode(ErrorCode.LESS_ADDTESTITEMCODE);
		} else {
			jsonResult.setResult("success");
		}
		return jsonResult;
	}

	/**
	 * base64转成图片,对字节数组字符串进行Base64解码并生成图片
	 * 
	 * @param imgStr
	 * @return
	 */
	public String GenerateImage(String imgStr) {

		if (StringUtils.isNotBlank(imgStr)) {
			try {
				/**
				 * Base64解码
				 */
				byte[] b = Base64.decode(imgStr);
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {
						b[i] += 256;
					}
				}
				/**
				 * 生成JPEG图片
				 */
				UUID uuid = UUID.randomUUID();
				String imgFilePath = "apache-tomcat-6.0.37/webapps/obpm/uploads/img/"
						+ uuid + ".jpg";
				OutputStream out = new FileOutputStream(imgFilePath);
				out.write(b);
				out.flush();
				out.close();
				return uuid.toString();
			} catch (Exception e) {
				imgStr = "";
				e.printStackTrace();
			}
		}
		return imgStr;
	}

	/**
	 * 图片转换Base64编码
	 * 
	 * @param filePath
	 * @return imgStr
	 * @throws Exception
	 */
	public static String imgToBase64String(String filePath) throws Exception {

		String imgStr = "";
		if (StringUtils.isNotBlank(filePath)) {
			URL url = new URL(filePath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(10 * 1000);
			InputStream inStream = conn.getInputStream();
			/**
			 * 得到图片的二进制数据，以二进制封装得到数据，具有通用性
			 */
			byte[] data = readInputStream(inStream);
			if (null != data) {
				imgStr = Base64.encode(data);
			}
		}
		return imgStr;
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {

		byte[] bt = null;
		if (null != inStream) {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			/**
			 * 每次读取的字符串长度，如果为-1，代表全部读取完毕
			 */
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			inStream.close();
			bt = outStream.toByteArray();
		}
		return bt;
	}

	/**
	 * 调用核心系统接口POST请求方法
	 * 
	 * @param content
	 *            ：请求JSON报文内容
	 * @param psotUrl
	 *            ：请求接口地址
	 * @param targetServiceCode
	 *            ：请求接口名称
	 * @return JsonResult
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation" })
	public static JsonResult postData(String content, String psotUrl,
			String targetServiceCode) throws Exception {

		JsonResult jsonResult = new JsonResult();
		/**
		 * 校验输入参数，如果失败则直接返回提示
		 */
		jsonResult = validatePostParams(content, psotUrl, targetServiceCode);
		if (jsonResult.getStatus() == 1) {
			return jsonResult;
		}

		logger.info("调用核心系统:targetServiceCode=================" + targetServiceCode);
		logger.info("调用核心系统:content===========================" + content);
		logger.info("调用核心系统:psotUrl===========================" + psotUrl);

		/**
		 * 发送POST请求
		 */
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(psotUrl);
		httpPost.setHeader("content-type", "text/plain");

		/**
		 * 发送参数内容进行UTF_8编码
		 */
		String encodedContent = new String(
				content.getBytes(KMCoreConstant.HTTP_CHARSET_UTF_8),
				KMCoreConstant.HTTP_CHARSET_ISO);
		/**
		 * 转换JSON格式
		 */
		String contentJson = JSONObject.fromObject(encodedContent).toString();
		httpPost.addHeader(HTTP.CONTENT_TYPE, KMCoreConstant.APPLICATION_JSON);
		/**
		 * 设置调用接口名称
		 */
		httpPost.addHeader("targetServiceCode", targetServiceCode);
		StringEntity se = new StringEntity(contentJson);
		se.setContentType(KMCoreConstant.CONTENT_TYPE_TEXT_JSON);
		se.setContentEncoding(KMCoreConstant.HTTP_CHARSET_UTF_8);
		httpPost.setEntity(se);

		/**
		 * 执行请求
		 */
		HttpResponse response = client.execute(httpPost);

		/**
		 * 返回请求接口的状态码
		 */
		int statusCode = response.getStatusLine().getStatusCode();
		logger.info("调用核心系统:statusCode==========================" + statusCode);
		/**
		 * 接口返回成功处理信息
		 */
		if (statusCode == KMCoreConstant.HTTP_REQ_OK) {
			String responseJson = "";
			InputStream inputStream = response.getEntity().getContent();
			/**
			 * 获取返回JSON结果
			 */
			responseJson = receivePost(inputStream);
			boolean isExp = isException(responseJson);
			if (isExp) {
				logger.info("调用核心系统返回异常：responseJson====================" + responseJson);
				jsonResult.addErrorCode(ErrorCode.KMCS_SYS_ERR);
				jsonResult.setResult("调用核心系统接口异常");
			} else {
				jsonResult.setResult(responseJson);
			}
			client.close();
		} else {
			client.close();
			jsonResult.addErrorCode(ErrorCode.KMCS_SYS_ERR);
			jsonResult.setResult("调用核心系统接口：" + targetServiceCode
					+ "系统异常，请求异常返回statusCode：" + statusCode);
		}
		return jsonResult;
	}

	/**
	 * 校验POST请求参数
	 * 
	 * @param content
	 * @param psotUrl
	 * @param targetServiceCode
	 * @return JsonResult
	 */
	private static JsonResult validatePostParams(String content,
			String psotUrl, String targetServiceCode) {

		JsonResult jsonResult = new JsonResult();
		if (StringUtils.isEmpty(content)) {
			jsonResult.addErrorCode(ErrorCode.LESS_POST_CONTENT);
			return jsonResult;
		} else if (StringUtils.isEmpty(psotUrl)) {
			jsonResult.addErrorCode(ErrorCode.LESS_POST_URL);
			return jsonResult;
		} else if (StringUtils.isEmpty(targetServiceCode)) {
			jsonResult.addErrorCode(ErrorCode.LESS_POST_SERVICECODE);
			return jsonResult;
		}
		return jsonResult;
	}

	/**
	 * 获取POST请求返回JSON结果
	 * 
	 * @param inputStream
	 * @return reqBody
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static String receivePost(InputStream inputStream)
			throws IOException, UnsupportedEncodingException {

		String reqBody = "";
		if (inputStream != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					inputStream));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			reqBody = URLDecoder.decode(sb.toString(),
					KMCoreConstant.HTTP_CHARSET_UTF_8);
		}
		return reqBody;
	}

	/**
	 * 返回结果是否包含exception异常
	 * 
	 * @param reqBody
	 * @return
	 */
	private static boolean isException(String reqBody) {
		return StringUtils.contains(reqBody, "exception");
	}

}
