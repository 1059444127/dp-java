package com.kingmed.dp.is.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author 作者 :
* @version 创建时间：2016年5月17日 上午11:45:37
* 类说明
 * 使用 GetMethod 来访问一个 URL 对应的网页,实现步骤: 1:生成一个 HttpClinet 对象并设置相应的参数。
 * 2:生成一个 GetMethod 对象并设置响应的参数。 3:用 HttpClinet 生成的对象来执行 GetMethod 生成的Get
 * 方法。 4:处理响应状态码。 5:若响应正常，处理 HTTP 响应内容。 6:释放连接。
 */
public class HttpRequestUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);
	
	private static HttpClient client = null;
	
	public static String sendGet(String url, String param){  
        StringBuilder sb = new StringBuilder();  
        InputStream ins = null;  
        String urlNameString = url + ("".equals(param)?"":("&" + param));
        /*  生成 GetMethod 对象并设置参数 */ 
        GetMethod method = new GetMethod(urlNameString);  
        // 设置 get 请求超时为 5 秒
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        // 设置请求重试处理，用的是默认的重试处理：请求三次
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,  
                new DefaultHttpMethodRetryHandler(3, false));
        
        
        // 设置 Http 连接超时为5秒
     	//client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        try {  
        	 /* 执行 HTTP GET 请求 */
            int statusCode = getClient().executeMethod(method);  
            System.out.println(statusCode);
            /*  判断访问的状态码 */
            if (statusCode == HttpStatus.SC_OK) {  
                ins = method.getResponseBodyAsStream();  
                byte[] b = new byte[1024];  
                int r_len = 0;  
                while ((r_len = ins.read(b)) > 0) {  
                    sb.append(new String(b, 0, r_len, method  
                            .getResponseCharSet()));  
                }  
            } else {  
                System.err.println("Response Code: " + statusCode);
                logger.debug(url+"发送GET请求失败！状态：" + statusCode);
            }  
        } catch (HttpException e) {  
            System.err.println("Fatal protocol violation: " + e.getMessage());  
            logger.debug(url+"发送GET请求出现异常！" + e);
        } catch (IOException e) {  
            System.err.println("Fatal transport error: " + e.getMessage());  
            logger.debug(url+"发送GET请求出现异常！" + e);
        } finally {  
        	/* 释放连接 */
            method.releaseConnection();  
            if (ins != null) {  
                try {
					ins.close();
				} catch (IOException e) {
					logger.debug(url+"发送GET请求关闭流异常！" + e);
					e.printStackTrace();
				}  
            }
//            if(typeNum == 2){//2个链接一组，每组结束后关闭连接
//            	client.getHttpConnectionManager().closeIdleConnections(1);//关闭连接
//            	client = null;
//            	typeNum = 0;
//            }
        }  
        System.out.println(sb.toString());  
        return sb.toString();
    }
	
	
	public static HttpClient getClient(){
		if(client == null){
			client = new HttpClient();
		}
		return client;
	}
}
