package com.kingmed.dp.modules.consultation.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.os.common.persistence.Page;
import org.os.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kingmed.dp.module.caseLib.entity.CaseLib;
import com.kingmed.dp.module.consultation.entity.CstCase;
import com.kingmed.dp.module.consultation.entity.CstHelpCapture;
import com.kingmed.dp.module.consultation.entity.CstSlide;
import com.kingmed.dp.module.consultation.entity.CstSlideScreenshot;
import com.kingmed.dp.module.consultation.entity.LbTiDefinitionsMaster;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.utils.UserUtils;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.modules.consultation.service.CstCaseService;
import com.kingmed.dp.modules.consultation.service.DefMasterService;

@Controller
@RequestMapping("cons/cstCase")
public class CstCaseController  extends BaseController{

	@Autowired
	private CstCaseService cstCaseService;
	@Autowired
	DefMasterService defMasterService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return "modules/consultation/cstCase_list";
	}
	
	@ResponseBody
	@RequestMapping("/searchList")
	public ResponseVO<CstCase> searchList(HttpServletRequest request,HttpServletResponse response,CstCase cstCase){
		cstCase.setUser(UserUtils.getUser());
		Page<CstCase> pages = cstCaseService.findPage(new Page<CstCase>(request,response), cstCase);
		return new ResponseVO<CstCase>(pages);
	}
	/**
	 * 查询检查项目列表   提供加做项目选择数据
	 * @param request
	 * @param response
	 * @param defMaster
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/projectList")
	public List<LbTiDefinitionsMaster> projectList(HttpServletRequest request,HttpServletResponse response,LbTiDefinitionsMaster defMaster){
		List<LbTiDefinitionsMaster> lists=null;
		try {
			lists = defMasterService.findList(defMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response,CstCase cstCase) throws IOException{
		ModelAndView view = new ModelAndView();
		CstCase result = cstCaseService.get(cstCase);
		view.addObject("cstCase", result);
		view.setViewName("modules/consultation/cstCase_detail");
		return view;
	}
	/**
	 * 查询会诊意见截图
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryImg")
	@ResponseBody
	public Object img(HttpServletRequest request,HttpServletResponse response){
		List<CstSlideScreenshot> list = cstCaseService.getImgs(request.getParameter("caseId"));
		return list;
	}
	/**
	 * 查询辅助图片
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryImgHelp")
	@ResponseBody
	public Object imgHelp(HttpServletRequest request,HttpServletResponse response){
		List<CstHelpCapture> list = cstCaseService.getImgsHelp(request.getParameter("caseId"));
		return list;
	}
	
	@RequestMapping("/update")
	public void doUpdate(HttpServletRequest request,HttpServletResponse response,CstCase cstCase) throws IOException{
		String result = "0";
		String titles = "添加会诊";
		if(cstCase.getId()!=null&&!"".equals(cstCase.getId())){
			titles = "会诊更新";
			cstCase.setCstPatExp(UserUtils.getUser().getId());
		}
		try {
			cstCaseService.save(cstCase);
			result = "1";
			LogUtils.saveLog(request, titles);
		} catch (Exception e) {
			result = "-1";
			LogUtils.saveLog(request, null,e,titles+"失败！");
			e.printStackTrace();
		}
		response.getWriter().write(result);
	}
	/**
	 * 提交最终诊断
	 * @param request
	 * @param response
	 * @param param
	 * @throws Exception
	 */
	@RequestMapping("/sendInfo")
	public void sendInfo(HttpServletRequest request,HttpServletResponse response,CstCase param) throws Exception{
		
		String result = "";
		String jsonText = request.getParameter("jsonText");//截图的json数据
		//String zdyj = request.getParameter("cstDia");//诊断意见
		String caseId = request.getParameter("caseId");//病理号
		//String companyId = request.getParameter("companyId");//子公司代码
		//String kmbarcode = request.getParameter("kmbarcode");//标本条码
		//String testCode = request.getParameter("testCode");//项目代码
		//String srcApp = request.getParameter("srcApp");//信息来源
		//String srcEnv = request.getParameter("srcEnv");//产生app环境
		//查出发送报告单地址
		String sendPath = "";
		CstCase params = new CstCase();
		CaseLib cLib = new CaseLib();
		cLib.setCaseId(caseId);
		params.setCaseLib(cLib);
		CstCase model = cstCaseService.get(params);
		if(model!=null){
			sendPath = model.getSendPath();
		}
		param.setSendPath(sendPath);
		String pathContent = request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getContextPath())+request.getContextPath().length());
		try {
			result = cstCaseService.sendInfo(caseId, jsonText,pathContent,param);
		} catch (Exception e) {
			result = "-2";
			LogUtils.saveLog(request, null,e,"提交最终诊断失败！");
			e.printStackTrace();
		}
			
		response.getWriter().write(result);
	}
	
	/**
	 * 数字切片跳转
	 * @param request
	 * @return
	 */
	@RequestMapping("/picture")
	public ModelAndView digitalSection(HttpServletRequest request,@RequestParam String caseId,@RequestParam String typed){
		ModelAndView view = new ModelAndView();
		List<CstSlide> lists = cstCaseService.getCstSlide(caseId);
		view.addObject("pictures", lists);
		view.addObject("typed", typed);
		if(lists!=null&&lists.size()>0)
			view.addObject("model", lists.get(0));
		view.setViewName("modules/consultation/picture");
		return view;
	}
	
	/**============================================已诊断==========================================*/
	/**
	 * 已诊断数据查询
	 */
	@RequestMapping("/listed")
	public String listed(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return "modules/consultation/cstCase_listed";
	}
	
	@ResponseBody
	@RequestMapping("/searchListed")
	public ResponseVO<CstCase> searchListed(HttpServletRequest request,HttpServletResponse response,CstCase cstCase){
		cstCase.setUser(UserUtils.getUser());
		cstCase.setState("1");
		Page<CstCase> pages = cstCaseService.findPage(new Page<CstCase>(request,response), cstCase);
		return new ResponseVO<CstCase>(pages);
	}
	@RequestMapping("/detailed")
	public ModelAndView detailed(HttpServletRequest request,HttpServletResponse response,CstCase cstCase) throws IOException{
		ModelAndView view = new ModelAndView();
		CstCase result = cstCaseService.get(cstCase);
		view.addObject("cstCase", result);
		view.setViewName("modules/consultation/cstCase_detailed");
		return view;
	}
}
