package com.kingmed.dp.modules.caseLib.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.os.common.persistence.Page;
import org.os.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.kingmed.dp.module.caseLib.entity.CaseLibSlide;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.modules.caseLib.service.CaseLibSlideService;

@Controller
@RequestMapping("cons/lib/caseLibSlide")
public class CaseLibSlideController  extends BaseController{

	@Autowired
	private CaseLibSlideService caseLibSlideService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return "modules/consultation/caseLibSlide_list";
	}
	
	@ResponseBody
	@RequestMapping("/searchList")
	public ResponseVO<CaseLibSlide> searchList(HttpServletRequest request,HttpServletResponse response,CaseLibSlide caseLibSlide){
		Page<CaseLibSlide> pages = caseLibSlideService.findPage(new Page<CaseLibSlide>(request,response), caseLibSlide);
		return new ResponseVO<CaseLibSlide>(pages);
	}
	@ResponseBody
	@RequestMapping("/listNoPage")
	public List<CaseLibSlide> listNoPage(HttpServletRequest request,HttpServletResponse response,CaseLibSlide caseLibSlide){
		List<CaseLibSlide> lists = caseLibSlideService.findList(caseLibSlide);
		return lists;
	}
	
	@RequestMapping(value="/update",method= RequestMethod.GET)
	public void update(HttpServletRequest request,HttpServletResponse response,CaseLibSlide caseLibSlide){
		try {
			String jsonStr = JSON.toJSONString(caseLibSlide);
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/picture")
	public ModelAndView picture(HttpServletRequest request,CaseLibSlide caseLibSlide){
		ModelAndView view = new ModelAndView();
		view.addObject("data", caseLibSlide);
		view.setViewName("modules/caseLib/picture_lib");
		return view;
	}
	/**
	 * 根据id查出数据库单条CaseLibSlide数据，每次请求都会先执行此方法，后在执行请求方法
	 * @param caseLibSlide
	 * @param map
	 */
	@ModelAttribute
	public void findCaseLibSlide(CaseLibSlide caseLibSlide,Map<String,Object> map){
		if(StringUtils.isNotBlank(caseLibSlide.getId())){
			CaseLibSlide model = caseLibSlideService.get(caseLibSlide.getId());
			map.put("caseLibSlide",model);
		}
	}
	@RequestMapping(value="/save",method= RequestMethod.POST)
	public void save(HttpServletRequest request,HttpServletResponse response,CaseLibSlide caseLibSlide) throws IOException{
		String result = "0";
		try {
			caseLibSlideService.save(caseLibSlide);
			result = "1";
		} catch (Exception e) {
			result = "-1";
			e.printStackTrace();
		}
		response.getWriter().write(result);
	}
	@RequestMapping(value="/update",method= RequestMethod.POST)
	public int doUpdate(HttpServletRequest request,HttpServletResponse response,CaseLibSlide caseLibSlide){
		int result = 0;
		String titles = "添加病例库切片";
		if(caseLibSlide.getId()!=null&&!"".equals(caseLibSlide.getId())){
			titles = "病例库切片更新";
		}
		try {
			caseLibSlideService.save(caseLibSlide);
			result = 1;
		} catch (Exception e) {
			result = -1;
			e.printStackTrace();
		}
		LogUtils.saveLog(request, titles);
		return result;
	}
	
	@RequestMapping(value="/del",method= RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response,CaseLibSlide model) throws IOException{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		if(StringUtils.isNotBlank(model.getId())){
			try {
				caseLibSlideService.delete(model);
				result.put("status", 1);
				LogUtils.saveLog(request, "删除病例库切片");
			} catch (Exception e) {
				result.put("status", 2);
				result.put("resultMsg", "删除失败！");
				e.printStackTrace();
			}
		}else{
			result.put("status", 2);
			result.put("resultMsg", "删除失败，id为空！");
		}
		String jsonStr = JSON.toJSONString(result);
		response.getWriter().write(jsonStr);
	}
}
