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
import com.kingmed.dp.module.caseLib.entity.ReadFilm;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.utils.OfficeUtils;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.module.sys.vo.TreeNode;
import com.kingmed.dp.modules.caseLib.service.CaseLibSlideService;
import com.kingmed.dp.modules.caseLib.service.ReadFilmService;

@Controller
@RequestMapping("cons/lib/readFilm")
public class ReadFilmController  extends BaseController{

	@Autowired
	private ReadFilmService readFilmService;
	@Autowired
	private CaseLibSlideService caseLibSlideService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return "modules/caseLib/readFilm_list";
	}
	
	@ResponseBody
	@RequestMapping("/searchList")
	public ResponseVO<ReadFilm> searchList(HttpServletRequest request,HttpServletResponse response,ReadFilm readFilm){
		Page<ReadFilm> pages = readFilmService.findPage(new Page<ReadFilm>(request,response), readFilm);
		return new ResponseVO<ReadFilm>(pages);
	}
	
	@ResponseBody
	@RequestMapping(value="/tree",method=RequestMethod.GET)
	public List<TreeNode> queryTree(HttpServletRequest request,HttpServletResponse response){
		List<ReadFilm> list = readFilmService.findAll();
		ReadFilm rf = new ReadFilm();
		rf.setId("0");
		rf.setName("读片会");
		if(!list.contains(rf)){
			list.add(rf);
		}
		List<TreeNode> readFilmTrees = OfficeUtils.queryOfficeTree(list);
		return readFilmTrees;
	}
	
	@RequestMapping(value="/detail",method= RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response,ReadFilm readFilm){
		ModelAndView view = new ModelAndView();
		CaseLibSlide slide = new CaseLibSlide();
		slide.setReadFilmId(readFilm.getId());
		List<CaseLibSlide> list = caseLibSlideService.findList(slide);
		view.addObject("list", list);
		view.addObject("readFilm", readFilm);
		view.setViewName("modules/caseLib/readFilm_detail");
		return view;
	}
	
	@RequestMapping(value="/update",method= RequestMethod.GET)
	public void update(HttpServletRequest request,HttpServletResponse response,ReadFilm readFilm){
		try {
			String jsonStr = JSON.toJSONString(readFilm);
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id查出数据库单条ReadFilm数据，每次请求都会先执行此方法，后在执行请求方法
	 * @param readFilm
	 * @param map
	 */
	@ModelAttribute
	public void findReadFilm(ReadFilm readFilm,Map<String,Object> map){
		if(StringUtils.isNotBlank(readFilm.getId())){
			ReadFilm model = readFilmService.get(readFilm.getId());
			map.put("readFilm",model);
		}
	}
	@RequestMapping(value="/save",method= RequestMethod.POST)
	public void save(HttpServletRequest request,HttpServletResponse response,ReadFilm readFilm) throws IOException{
		String result = "0";
		try {
			readFilmService.save(readFilm);
			result = "1";
		} catch (Exception e) {
			result = "-1";
			e.printStackTrace();
		}
		response.getWriter().write(result);
	}
	@RequestMapping(value="/update",method= RequestMethod.POST)
	public void doUpdate(HttpServletRequest request,HttpServletResponse response,ReadFilm readFilm) throws IOException{
		int result = 0;
		String titles = "添加读片会数据";
		if(readFilm.getId()!=null&&!"".equals(readFilm.getId())){
			titles = "读片会数据更新";
		}
		try {
			readFilmService.save(readFilm);
			result = 1;
			LogUtils.saveLog(request, titles);
		} catch (Exception e) {
			result = -1;
			e.printStackTrace();
		}
		response.getWriter().write(result);
	}
	
	@RequestMapping(value="/del",method= RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response,ReadFilm model) throws IOException{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		if(StringUtils.isNotBlank(model.getId())){
			try {
				readFilmService.delete(model);
				result.put("status", 1);
				LogUtils.saveLog(request, "删除读片会数据");
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
