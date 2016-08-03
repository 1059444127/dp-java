package com.kingmed.dp.modules.consultation.web;


import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.os.common.utils.StringUtils;
import org.os.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.kingmed.dp.module.consultation.entity.CstSlideScreenshot;
import com.kingmed.dp.module.consultation.entity.FileParam;
import com.kingmed.dp.module.consultation.utils.Constants;
import com.kingmed.dp.module.consultation.utils.ImageUtil;
import com.kingmed.dp.modules.consultation.service.FileUploadService;


/***
 * 
 * @author ltw_pc
 * modify by ltw_pc at 2015年10月16日  14：02
 * desc:修复提交时图片无法保存bug
 */

@Controller
@RequestMapping("cons/file")
public class FileUpLoadController  extends BaseController {
	
	@Autowired
	private FileUploadService fileUploadService;
	//add by ltw at 2015-12-01
	private static final Logger LOGGER =Logger.getLogger(FileUpLoadController.class);
	
	
	/***
	 * 截图上传
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public void update(HttpServletRequest request,FileParam fileParam) throws Exception{
		System.out.println("执行更新截图操作");
		//获取服务器的绝对路径
		String absolutePath = request.getSession().getServletContext().getRealPath("");
		//以年份作为存放截图文件的目录名
		String year = new SimpleDateFormat("yyyy").format(new Date());
		//设置文件保存目录
		File destFileDir=new File(absolutePath+"/uploads/item/"+year);
		if(!destFileDir.exists())
		{
			destFileDir.mkdirs();
		}

		/* commented by zhengjunjie on 2016-03-11
		fileName=fileName.substring(0, fileName.indexOf(".jpg"))+"-"+UUID.randomUUID().toString()+".jpg";
		File destFile=new File(absolutePath+"uploads/item/"+year+"/"+fileName);
		String jsonText="{name="+fileName+",path=/uploads/item/"+year+"/"+fileName+"}";
		*/

		File destFile=new File(absolutePath+"/uploads/item/"+year+"/"+fileParam.getPhysicalFilename());
		String jsonText="[{\"name\":\""+fileParam.getFileName()+"\",\"path\":\"/uploads/item/"+year+"/"+fileParam.getPhysicalFilename()+"\"}]";

		/***
		 * 保存江丰或者其他的截图到本地add by ltw at 2015-11-26
		 *  modify by ltw at 2015-12-01
		 */
		InputStream inputStream =null;
		try {
			if(fileParam.getFileData()==null && StringUtils.isNotBlank(fileParam.getBarcode()))
			{
				ResourceBundle bundle = ResourceBundle.getBundle("systempath");
				//comment by ltw at 2015-12-01
				//String JFServerIP = bundle.getString("JFServerIP");
				String jFServerPort = bundle.getString("JFServerPort");
				URL url = new URL("http://"+fileParam.getjFServerIP()+":"+jFServerPort+"/"+Constants.JFJTDir+"/"+fileParam.getBarcode()+"/"+fileParam.getjFFileName()+".jpg");
				URLConnection connection = url.openConnection();
				inputStream = connection.getInputStream();
				//comment by zhanglei at 2016-5-18
				//复制图片到指定目录 控制宽高485*285
				FileUtils.copyInputStreamToFile(ImageUtil.resize(inputStream), destFile);
				
				/*comment by zhanglei at 2016-5-18
				 * 截图上传成功后，保存本地原图（没有缩放处理的）D:\img\*/
				//设置文件保存目录
				File yFileDir=new File(bundle.getString("originalAddress"));
				if(!yFileDir.exists())
				{
					yFileDir.mkdirs();
				}
				String yFile=bundle.getString("originalAddress")+fileParam.getPhysicalFilename();
				//生成本地图片
				ImageUtil.inputstreamtofile(url.openConnection().getInputStream(), yFile);
			}else{
				FileUtils.copyFile(fileParam.getFileData(), destFile);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}finally{
			//关闭输入流  modify by ltw  at 2015-12-01
			if(inputStream!=null)
			{
				inputStream.close();
			}
		}
		//comment by ltw at 2015-11-30
		//Thread.sleep(500);
		//更新到数据库
		fileUploadService.updateFileByTestId(fileParam.getTestId(), fileParam.getFileName(),jsonText);
		System.out.println("上传成功=========");
	}
	/***
	 * 删除指定的截图
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping("delete")
	public void delete(HttpServletRequest request,HttpServletResponse response,FileParam fileParam) throws Exception{
		System.out.println("执行删除截图操作");
		int n = fileUploadService.deleteDocByJtName(fileParam.getTestId(), fileParam.getFilePath(),fileParam.getId());
		System.out.println("删除返回的标志是===="+n);
		response.getWriter().write(Integer.toString(n));
	}
	/***
	 * 查询所有的截图
	 * @throws Exception
	 */
	@RequestMapping("/findAll")
	public void findAll(HttpServletRequest request,HttpServletResponse response,FileParam fileParam) throws Exception{
		System.out.println("执行查询所有截图的操作");
		List<CstSlideScreenshot> lists = fileUploadService.getByCaseId(fileParam.getTestId());
		String jsons = JSON.toJSONString(lists);
		System.out.println("返回的结果"+jsons);
		request.getSession().setAttribute("jtJson", jsons);
		response.getWriter().write(jsons);
	
	}
	
}
