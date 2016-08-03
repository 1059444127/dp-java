package cn.myapps.client;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.UUID;

import javax.xml.rpc.holders.StringHolder;

import org.apache.axis.encoding.Base64;
import org.apache.commons.lang.StringUtils;
import org.tempuri.IPathProxy;

import cn.myapps.webservice.client.DocumentServiceProxy;
import cn.myapps.webservice.fault.DocumentServiceFault;

public class LIRService {

	IPathProxy iPathProxy = new IPathProxy();

	/**
	 * 添加短信记录
	 *
	 * @param number
	 *            手机号码
	 * @param content
	 *            短信内容
	 * @param type
	 *            短信类型
	 * @param operation_state
	 *            操作类型
	 * @param ci_id
	 *            病例ID
	 * @param test_id
	 *            病理号
	 */
	/*public int createMSG(String number, String content, String type,
			String operation_state, String ci_id, String test_id) {
		DocumentServiceProxy p = new DocumentServiceProxy(
				"http://127.0.0.1:8090/obpm/services/DocumentService?wsdl");
		String formName = "km_message";
		String applicationId = "11e4-868e-476f6c83-afc0-bf7d9fc712b0";
		String domainUserId = "11e4-d9d5-6f2a1de2-87cf-356da46a08eb";
		HashMap<String, Object> parameters = null;
		parameters = new HashMap<String, Object>();
		parameters.put("receive_number", number);
		parameters.put("msg_content", content);
		parameters.put("msg_type", type);
		parameters.put("operation_state", operation_state);
		parameters.put("ci_id", ci_id);
		parameters.put("msg_test_id", test_id);
		try {
			int re = p.createDocumentByDomainUser(formName, parameters,
					domainUserId, applicationId);
			System.out.println("code=" + re);
			return re;
		} catch (DocumentServiceFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return -1;
	}
*///Commented by longtingwei on 20151104
	/**
	 * 发送诊断意见
	 *
	 * @param aSiteCode
	 *            公司代码
	 * @param aRequestCode
	 *            条码号
	 * @param aTestNum
	 *            病理号
	 * @param aContent
	 *            诊断意见
	 * @param address
	 *            接口地址
	 *
	 * @return 0：成功； -1：失败
	 * @throws RemoteException
	 */
	public String sendPathDiagnosisinfo(String aSiteCode, String aRequestCode,
			String aTestNum, String aContent, String address) {
		StringHolder msg = new StringHolder();
		StringHolder _return = new StringHolder();
		try {
			if (StringUtils.isNotBlank(address)) {
				iPathProxy.setEndpoint(address);
				iPathProxy.sendPathDiagnosisinfo(aSiteCode, aRequestCode,
						aTestNum, aContent, msg, _return);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return _return.value;
	}

	/**
	 * 发送截图
	 *
	 * @param aSiteCode
	 *            公司代码
	 * @param aRequestCode
	 *            条码号
	 * @param aTestNum
	 *            病理号
	 * @param aPicture
	 *            图片内容（注：将图片转换成base64字符）。
	 * @param address
	 *            接口地址
	 * @return 0：成功； -1：失败
	 * @throws Exception
	 */
	public String sendPathPictureinfo(String aSiteCode, String aRequestCode,
			String aTestNum, String aPicture, String aPicName, String aPicDesc,
			String address) throws Exception {
		StringHolder msg = new StringHolder();
		StringHolder _return = new StringHolder();
		String imageStr = GetImageStr(aPicture);
		try {
			if (StringUtils.isNotBlank(address)) {
				iPathProxy.setEndpoint(address);
				iPathProxy.sendPathPictureinfo(aSiteCode, aRequestCode,
						aTestNum, imageStr, aPicName, aPicDesc, msg, _return);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _return.value;
	}

	/**
	 * 发送反馈信息
	 *
	 * @param aSiteCode
	 *            公司代码
	 * @param aparentid
	 *            病例主ID
	 * @param aRequestCode
	 *            条码号
	 * @param aTestNum
	 *            病理号
	 * @param aContent
	 *            消息内容
	 * @param aCreatePerson
	 *            发送人
	 * @param address
	 *            接口地址
	 * @return 0：成功； -1：失败
	 */
	public String sendExpertMSGinfo(String aSiteCode, String aparentid,
			String aRequestCode, String aTestNum, String aContent,
			String aCreatePerson, String address) {
		StringHolder msg = new StringHolder();
		StringHolder _return = new StringHolder();
		try {
			if (StringUtils.isNotBlank(address)) {
				iPathProxy.setEndpoint(address);
				iPathProxy.sendExpertMSGinfo(aSiteCode, aparentid,
						aRequestCode, aTestNum, aContent, aCreatePerson, msg,
						_return);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return _return.value;
	}

	/**
	 * 加做项目
	 *
	 * @param aSiteCode
	 *            公司代码
	 * @param aRequestCode
	 *            条码号
	 * @param aItemCode
	 *            标本项目代码
	 * @param aAddItemCode
	 *            加做项目代码
	 * @param address
	 *            接口地址
	 * @return 0：成功； -1：失败
	 */
	public String sendAddItemInfo(String aSiteCode, String aDocumentID,
			String aRequestCode, String aItemCode, String aAddItemCode,
			String address) {
		StringHolder msg = new StringHolder();
		StringHolder _return = new StringHolder();
		try {
			if (StringUtils.isNotBlank(address)) {
				iPathProxy.setEndpoint(address);
				iPathProxy.sendAddItemInfo(aSiteCode, aDocumentID,
						aRequestCode, aItemCode, aAddItemCode, msg, _return);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return _return.value;
	}

	/**
	 * 图片转化成base64字符串
	 *
	 * @param filePath
	 *            图片路径
	 * @return base64
	 * @throws Exception
	 */
	public static String GetImageStr(String filePath) throws Exception {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理

		return imgToBase64String(filePath);

		// String imgFile = filePath;// 待处理的图片
		// URL url = new URL(imgFile);
		// HttpURLConnection onn = (HttpURLConnection)url.openConnection();
		// onn.setRequestMethod("GET");
		// System.out.println(imgFile);
		// InputStream in = null;
		// byte[] data = null;
		// byte[] b = null;
		// byte [] s = null;
		// // 读取图片字节数组
		// try {
		// in = onn.getInputStream();
		// //Thread.sleep(5000);
		// System.out.println(in.available() + "=============");
		// data = new byte[1024];
		// List<byte[]> list = new ArrayList<byte[]>();
		// int i = 0;
		// int sum = 0;
		// while(( i = in.read(data) ) != -1){
		// list.add(data);
		// sum +=i;
		// data = new byte[1024];
		// }
		// s = new byte[sum];
		// System.out.println(s.length);
		// int z=0;
		// for(byte [] zz : list){
		// for(int y=0;y!=zz.length && z != sum;++y){
		// s[z]=zz[y];
		// z++;
		// }
		// }
		//
		//
		// in.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// // 对字节数组Base64编码
		//
		// String r = Base64.encode(s);
		// // System.out.println(r);
		//
		// try {
		// FileOutputStream out = new FileOutputStream("d:\\FileNam6s.txt");
		// out.write(r.getBytes());
		// out.close();
		// } catch (Exception e) {
		// System.out.println("BASE64写入文件失败");
		// e.printStackTrace();
		// }
		//
		// return null;
	}

	/**
	 * base64转成图片
	 *
	 * @param imgStr
	 * @return
	 */
	/*public String GenerateImage(String imgStr) { // 对字节数组字符串进行Base64解码并生成图片
		System.out.println(imgStr);
		if (imgStr == null) // 图像数据为空
			return "";
		try {
			// Base64解码
			byte[] b = Base64.decode(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			UUID uuid = UUID.randomUUID();
			String imgFilePath = "apache-tomcat-6.0.37/webapps/obpm/uploads/img/"
					+ uuid + ".jpg";// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return uuid.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}*///Commented by longtingwei on 20151111 此api是为了显示缩略图，显示缩略图使用其他的做法

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
		/*try {
			FileOutputStream out = new FileOutputStream(
					"d:\\imgBase64String.txt");
			out.write(r.getBytes());
			out.close();
		} catch (Exception e) {
			System.out.println("BASE64写入文件失败");
			e.printStackTrace();
		}
		System.out.println("获得图片的内容" + r);*///Commented by longtingwei on 20151104 因为作为测试用途
		return r;
		// //new一个文件对象用来保存图片，默认保存当前工程根目录
		// File imageFile = new File("d:\\FileName9.jpg");
		// //创建输出流
		// FileOutputStream outStream = new FileOutputStream(imageFile);
		// //写入数据
		// outStream.write(data);
		// //关闭输出流
		// outStream.close();
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

	/***
	 * 发送报告单
	 *
	 * @param aSiteCode
	 *            公司代码
	 * @param aRequestCode
	 *            条码号
	 * @param aTestNum
	 *            病理号
	 * @param aItemCode
	 *            标本项目代码
	 * @param aReportName
	 *            报告单名称
	 * @param aReportDesc
	 *            报告单描述
	 * @param aReportSize
	 *            报告单类型
	 * @param reportPath
	 *            报告单路径
	 * @param address
	 *            接口地址
	 * @return 0：成功； -1：失败
	 * @throws Exception
	 */
	public String SendPicReport(String aSiteCode, String aRequestCode,
			String aTestNum, String aItemCode, String aReportName,
			String aReportDesc, String aReportSize, String reportPath,
			String address) throws Exception {
		StringHolder msg = new StringHolder();
		StringHolder _return = new StringHolder();
		FileInputStream fileInputStream = new FileInputStream(reportPath);
		byte[] data = readInputStream(fileInputStream);
		String aReportPic = Base64.encode(data);
		/*
		 * System.out.println("写入到磁盘的路径是=============="+"d:\\"+aReportName+".txt"
		 * ); FileOutputStream out = new
		 * FileOutputStream("d:\\"+aReportName+".txt");
		 * out.write(aReportPic.getBytes()); out.close();
		 */
		if (StringUtils.isNotBlank(address)) {
			iPathProxy.setEndpoint(address);
		}
		iPathProxy
				.sendPicReport(aSiteCode, aRequestCode, aTestNum, aItemCode,
						aReportName, aReportDesc, aReportSize, aReportPic, msg,
						_return);
		String str = reportPath.substring(0, reportPath.indexOf(aTestNum) - 1);
		//FileUtils.delAllFile(str);  //Commented by zhengjunjie on 20151104
		//new File(str).delete();  //Commented by zhengjunjie on 20151104
		//Added by zhengjunjie on 20151104
		//this.deleteFile(new File(str));
		return _return.value;
	}

	//Added by zhengjunjie on 20151104
	private void deleteFile(File f) {
		if (f.isFile()) {
			f.delete();
			return;
		}

		File[] fs = f.listFiles();
		for (File file : fs) {
			deleteFile(file);
		}

		f.delete();
	}

}
