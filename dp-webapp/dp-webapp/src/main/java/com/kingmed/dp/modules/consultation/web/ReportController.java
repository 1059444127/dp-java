package com.kingmed.dp.modules.consultation.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.os.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingmed.dp.modules.consultation.service.CstSlideScreenshotService;
import com.kingmed.dp.modules.consultation.service.ReportService;

/***
 * 
 * @author 龙庭伟 at 2015年10月19 下午15：08
 * desc:生成，查看报告单
 * @version 1.0
 * @since JDK 1.6
 */
@Controller
@RequestMapping("cons/report")
public class ReportController  extends BaseController {
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private CstSlideScreenshotService screenshotService;
	
	//生成报告单
	@RequestMapping("/createReport")
	public void createReport(HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println("调用service方法==============");
		String format = request.getParameter("format");
		String caseId = request.getParameter("caseId");
		String cptSource = "0";
		List list = screenshotService.getImgs(caseId);
		if(list!=null&&list.size()>0){
			cptSource = list.size()+"";
		}
		String reportPath = reportService.createReport(format, cptSource, caseId);
		response.getWriter().write(reportPath);
	/*	DPDiagnoseLog log = new DPDiagnoseLog();
		log.setId(UUID.randomUUID().toString());
		log.setFlag(Constants.UNSEND);
		log.setTestId(testId);
		log.setLoginUser(user);
		log.setRemark("生成报告单");*/
//		ServletActionContext.getRequest().getSession().setAttribute("filePath", filePath);
//		return filePath; comment by ltw at 2015-11-30
	}
	/*//查看报告单
	public String queryReport(String  path,String testId)
	{
	   return reportService.queryReport(path, testId);
	}
	*/
}
