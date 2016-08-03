package com.kingmed.dp.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.os.common.persistence.Page;
import org.os.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingmed.dp.module.sys.entity.Log;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.modules.sys.service.LogService;

@Controller
@RequestMapping(value="/admin/log")
public class LogController extends BaseController {

	@Autowired
	private LogService logService;
	
	@RequestMapping(value="view", method=RequestMethod.GET)
	public String logView(Model model) {
		return "modules/sys/log_list";
	}
	
	@RequestMapping(value="list", method=RequestMethod.POST)
	public @ResponseBody ResponseVO<Log> findlist(HttpServletRequest request, HttpServletResponse response, Log log) {
		logger.info("search:/admin/log/list,return:ResponseVO<Log>");
		Page<Log> pages = logService.findList(new Page<Log>(request,response), log);//logService.findPage(new Page<Log>(request,response), log);
		return new ResponseVO<Log>(pages);
	}
}
