package com.kingmed.dp.modules.consultation.web;

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

import com.alibaba.fastjson.JSON;
import com.kingmed.dp.module.consultation.entity.CstAdvice;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.modules.consultation.service.CstAdviceService;

@Controller
@RequestMapping("cons/cstAdvice")
public class CstAdviceController  extends BaseController{

	@Autowired
	private CstAdviceService cstAdviceService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return "modules/consultation/cstAdvice_list";
	}
	
	@ResponseBody
	@RequestMapping("/searchList")
	public ResponseVO<CstAdvice> searchList(HttpServletRequest request,HttpServletResponse response,CstAdvice cstAdvice){
		Page<CstAdvice> pages = cstAdviceService.findPage(new Page<CstAdvice>(request,response), cstAdvice);
		return new ResponseVO<CstAdvice>(pages);
	}
	@ResponseBody
	@RequestMapping("/listNoPage")
	public List<CstAdvice> listNoPage(HttpServletRequest request,HttpServletResponse response,CstAdvice cstAdvice){
		List<CstAdvice> lists = cstAdviceService.findList(cstAdvice);
		return lists;
	}
	
	@RequestMapping(value="/update",method= RequestMethod.GET)
	public void update(HttpServletRequest request,HttpServletResponse response,CstAdvice cstAdvice){
		try {
			String jsonStr = JSON.toJSONString(cstAdvice);
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据id查出数据库单条CstAdvice数据，每次请求都会先执行此方法，后在执行请求方法
	 * @param cstAdvice
	 * @param map
	 */
	@ModelAttribute
	public void findCstAdvice(CstAdvice cstAdvice,Map<String,Object> map){
		if(StringUtils.isNotBlank(cstAdvice.getId())){
			CstAdvice model = cstAdviceService.get(cstAdvice.getId());
			map.put("cstAdvice",model);
		}
	}
	@RequestMapping(value="/save",method= RequestMethod.POST)
	public void save(HttpServletRequest request,HttpServletResponse response,CstAdvice cstAdvice) throws IOException{
		String result = "0";
		try {
			cstAdviceService.save(cstAdvice);
			result = "1";
		} catch (Exception e) {
			result = "-1";
			e.printStackTrace();
		}
		response.getWriter().write(result);
	}
	@RequestMapping(value="/update",method= RequestMethod.POST)
	public int doUpdate(HttpServletRequest request,HttpServletResponse response,CstAdvice cstAdvice){
		int result = 0;
		String titles = "添加反馈意见";
		if(cstAdvice.getId()!=null&&!"".equals(cstAdvice.getId())){
			titles = "反馈意见更新";
		}
		try {
			cstAdviceService.insert(cstAdvice);
			result = 1;
		} catch (Exception e) {
			result = -1;
			e.printStackTrace();
		}
		LogUtils.saveLog(request, titles);
		return result;
	}
	
	@RequestMapping(value="/del",method= RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response,CstAdvice model) throws IOException{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		if(StringUtils.isNotBlank(model.getId())){
			try {
				cstAdviceService.delete(model);
				result.put("status", 1);
				LogUtils.saveLog(request, "删除反馈意见");
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
