package com.kingmed.dp.modules.sys.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.os.common.persistence.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingmed.dp.module.sys.entity.JobIsParams;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.modules.sys.service.JobIsParamsService;

@Controller
@RequestMapping(value="/admin/job")
public class JobIsParamsController {

	private Logger logger = LoggerFactory.getLogger(JobIsParamsController.class);
	
	@Autowired
	private JobIsParamsService jobIsParamsService;
	
	@RequestMapping(value="/manage",method=RequestMethod.GET)
	public String manage() {
		logger.info("/admin/job/manage");
		return "modules/jobIsParams/jobIsParams";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public @ResponseBody ResponseVO<JobIsParams> list(HttpServletRequest request, 
			HttpServletResponse response, JobIsParams job) {
		logger.info("/admin/job/list, return: ResponseVO<JobIsParams>");
		try{
			Page<JobIsParams> pages = jobIsParamsService.findPage(new Page<JobIsParams>(request,response), job);
			return new ResponseVO<JobIsParams>(pages);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> edit(HttpServletRequest request, String id) {
		logger.info("/admin/job/edit, return:Map<String, Object>");
//		try {
		Map<String, Object> map = jobIsParamsService.findJobIsParams(id);
		return map;
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> save(HttpServletRequest request, JobIsParams job) {
		logger.info("/admin/job/save, return:Map<String, Object>");
		Map<String, Object> map = null;
		
//		try{
			map = jobIsParamsService.saveJob(job);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		return map;
	}
	
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> del(HttpServletRequest request, String id) {
		logger.info("/admin/job/del, return:Map<String, Object>");
		Map<String, Object> map = jobIsParamsService.delJobIsParams(id);
		return map;
	}
}
