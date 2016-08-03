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
import com.kingmed.dp.module.caseLib.entity.CaseLib;
import com.kingmed.dp.module.caseLib.entity.RecentVisit;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.modules.caseLib.service.CaseLibService;
import com.kingmed.dp.modules.caseLib.service.RecentVisitService;

@Controller
@RequestMapping("cons/lib/caseLib")
public class CaseLibController  extends BaseController{

	@Autowired
	private CaseLibService caseLibService;
	@Autowired
	private RecentVisitService recentVisitService;
	
	@RequestMapping(value="/index",method= {RequestMethod.GET,RequestMethod.POST})
	public String index(HttpServletRequest request,HttpServletResponse response){
		String viewType = request.getParameter("viewType");
		if(viewType!=null&&!viewType.isEmpty()){
			caseLibService.setType(viewType);
		}
		request.setAttribute("viewType", caseLibService.getType());
		return "modules/caseLib/index";
	}
	@ResponseBody
	@RequestMapping(value="/searchList",method= {RequestMethod.GET,RequestMethod.POST})
	public ResponseVO<CaseLib> searchList(HttpServletRequest request,HttpServletResponse response,CaseLib caseLib){
		Page<CaseLib> pages = caseLibService.findPage(new Page<CaseLib>(request,response), caseLib);
		return new ResponseVO<CaseLib>(pages);
	}
	
	@RequestMapping(value="/detail",method= RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response,CaseLib caseLib){
		ModelAndView view = new ModelAndView();
		//根据病例库切片id查询该切片最近的访问用户
		RecentVisit item = new RecentVisit();
		item.setCaseLibSlide(caseLib.getCaseLibSlide());
		List<RecentVisit> recentVisits = recentVisitService.findLateList(item);
		view.addObject("number", recentVisitService.findListCount(item)+1);//浏览次数
		view.addObject("recentVisits", recentVisits);//最近访问数据集合
		view.addObject("caseLib", caseLib);
		view.setViewName("modules/caseLib/slide_detail");
		return view;
	}
	
	@RequestMapping(value="/update",method= RequestMethod.GET)
	public void update(HttpServletRequest request,HttpServletResponse response,CaseLib caseLib){
		try {
			String jsonStr = JSON.toJSONString(caseLib);
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据id查出数据库单条Dict数据，每次请求都会先执行此方法，后在执行请求方法
	 * @param dict
	 * @param map
	 */
	@ModelAttribute
	public void findCaseLib(CaseLib caseLib,Map<String,Object> map){
		if(StringUtils.isNotBlank(caseLib.getId())){
			CaseLib model = caseLibService.get(caseLib.getId());
			map.put("caseLib",model);
		}
	}
	
	@RequestMapping(value="/update",method= RequestMethod.POST)
	public void doUpdate(HttpServletRequest request,HttpServletResponse response,CaseLib caseLib){
		String titles = "添加区域";
		if(caseLib.getId()!=null&&!"".equals(caseLib.getId())){
			titles = "区域更新";
		}
		caseLibService.save(caseLib);
		LogUtils.saveLog(request, titles);
	}
	
	@RequestMapping(value="/del",method= RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response,CaseLib model) throws IOException{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		if(StringUtils.isNotBlank(model.getId())){
			try {
				caseLibService.delete(model);
				result.put("status", 1);
				LogUtils.saveLog(request, "区域删除");
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
