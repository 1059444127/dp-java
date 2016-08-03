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
import com.kingmed.dp.module.caseLib.entity.PatType;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.utils.OfficeUtils;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.module.sys.vo.TreeNode;
import com.kingmed.dp.modules.caseLib.service.PatTypeService;

@Controller
@RequestMapping("cons/lib/patType")
public class PatTypeController  extends BaseController{

	@Autowired
	private PatTypeService patTypeService;
	
	@RequestMapping(value="/list",method= {RequestMethod.GET,RequestMethod.POST})
	public String list(HttpServletRequest request,HttpServletResponse response){
		return "modules/caseLib/patType_list";
	}
	
	@ResponseBody
	@RequestMapping(value="/searchList",method= {RequestMethod.GET,RequestMethod.POST})
	public ResponseVO<PatType> searchList(HttpServletRequest request,HttpServletResponse response,PatType patType){
		Page<PatType> pages;
		try {
			pages = patTypeService.findPage(new Page<PatType>(request,response), patType);
			return new ResponseVO<PatType>(pages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseVO<PatType>(new Page<PatType>());
	}
	
	@ResponseBody
	@RequestMapping(value="/tree",method=RequestMethod.GET)
	public List<TreeNode> queryTree(HttpServletRequest request,HttpServletResponse response){
		List<TreeNode> patTypeTrees = OfficeUtils.queryOfficeTree(patTypeService.findAll());
		return patTypeTrees;
	}
	
	@RequestMapping(value="/detail",method= RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response,PatType patType){
		ModelAndView view = new ModelAndView();
		view.addObject("patType", patType);
		view.setViewName("modules/caseLib/detail");
		return view;
	}
	@RequestMapping(value="/update",method= RequestMethod.GET)
	public void update(HttpServletRequest request,HttpServletResponse response,PatType patType){
		try {
			String jsonStr = JSON.toJSONString(patType);
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
	public void findCaseLib(PatType patType,Map<String,Object> map){
		if(StringUtils.isNotBlank(patType.getId())){
			PatType model = patTypeService.get(patType.getId());
			map.put("patType",model);
		}
	}
	
	@RequestMapping(value="/update",method= RequestMethod.POST)
	public void doUpdate(HttpServletRequest request,HttpServletResponse response,PatType patType){
		String titles = "添加病例库类型";
		if(patType.getId()!=null&&!"".equals(patType.getId())){
			titles = "病例库类型更新";
		}
		try {
			patTypeService.save(patType);
			LogUtils.saveLog(request, titles);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@RequestMapping(value="/del",method= RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response,PatType model) throws IOException{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		if(StringUtils.isNotBlank(model.getId())){
			try {
				patTypeService.delete(model);
				result.put("status", 1);
				LogUtils.saveLog(request, "病例库类型删除");
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
