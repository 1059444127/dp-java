package com.kingmed.dp.gateway.client;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.rpc.holders.StringHolder;

import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;
import org.tempuri.IPath;
import org.tempuri.IPathProxy;

import com.kingmed.dp.gateway.dto.SlideDto;
import com.kingmed.dp.gateway.util.HttpRequest;
import com.kingmed.dp.gateway.util.ModelUtil;

public class LISService {
	
	private static Logger logger = Logger.getLogger(LISService.class);

	public static LISService lISService;
	
	IPath iPath= new IPathProxy();
	
	private LISService() {
	}
	
	public static LISService getInstance() {
		if(lISService==null){
			lISService = new  LISService();
		}
		return lISService;
	}
	
	public String sendResultInfo(SlideDto slide ){
		String result = "";
		StringHolder msg = new StringHolder();
    	StringHolder _return = new StringHolder();
    	//登陆dp-webapp系统，获取sessionid
    	String cookie = HttpRequest.postMethodLogin(ModelUtil.dp_url+"/dp/login",ModelUtil.getpointLoginData());
    	String point = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/updateAll/queryPointUrl",ModelUtil.getUrlParamData(slide),cookie);//调接口判断是否拼接url，point为空则不拼接
    	if("".equals(point)){
    		logger.info("上传数字切片，dp-webapp登陆失败，请检查用户名密码是否正确！");
    		logger.debug("username:"+ModelUtil.POINT_USERNAME+",password:"+ModelUtil.POINT_PASSWORD);
    	}
    	logger.info("上传数字切片拼接的url:"+point+",参数中的url:"+slide.getUrl());
    	try {
			iPath.sendResultInfo(slide.getBarcode(), slide.getUrl(), slide.getScannerModel(), slide.getLabelWithOverview(), msg, _return);
    		logger.info("上传数字切片web Service返回结果："+_return.value);
			//向dp-webapp中保存数据
			String dpResult = "";
			if(!"".equals(cookie)){
				dpResult = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/updateAll/slide",ModelUtil.getSlideMap(slide),cookie);
			}
			if("1".equals(dpResult)&&"0".equals(_return.value)){
				result = "0";
			}else{
				result = "-1";
				logger.info("上传数字切片失败：_return.value="+_return.value+",dpResult="+dpResult);
			}
			logger.debug(slide.toString());
    		logger.debug("上传数字切片重组的url："+ModelUtil.lisUrl+slide.getPath()+"/"+slide.getBarcode()+"?"+slide.getToken());
		} catch (Exception e) {
			logger.error("barcode："+slide.getBarcode());
			logger.error("上传数字切片数据执行异常："+e);
			e.printStackTrace();
		}
    	return result;
	} 
	
	/**
     * 图片转化成base64字符串
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String imgToBase64String(String filePath) throws Exception {
		// new一个URL对象
		URL url = new URL(filePath);
		// 打开链接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置请求方式为"GET"
		conn.setRequestMethod("GET");
		// 超时响应时间为5秒
		conn.setConnectTimeout(10 * 1000);
		// 通过输入流获取图片数据
		InputStream inStream = conn.getInputStream();
		// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
		byte[] data = readInputStream(inStream);

		String r = Base64.encode(data);
		return r;
	}
    
    public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存 ip:10.5.6.130
		return outStream.toByteArray();
	}
	
}
