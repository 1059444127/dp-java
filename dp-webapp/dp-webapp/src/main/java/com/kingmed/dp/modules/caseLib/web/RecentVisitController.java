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

import com.alibaba.fastjson.JSON;
import com.kingmed.dp.module.caseLib.entity.RecentVisit;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.modules.caseLib.service.RecentVisitService;

@Controller
@RequestMapping("cons/lib/recentVisit")
public class RecentVisitController  extends BaseController{

	@Autowired
	private RecentVisitService recentVisitService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return "modules/consultation/recentVisit_list";
	}
	
	@ResponseBody
	@RequestMapping("/searchList")
	public ResponseVO<RecentVisit> searchList(HttpServletRequest request,HttpServletResponse response,RecentVisit recentVisit){
		Page<RecentVisit> pages = recentVisitService.findPage(new Page<RecentVisit>(request,response), recentVisit);
		return new ResponseVO<RecentVisit>(pages);
	}
	@ResponseBody
	@RequestMapping("/listNoPage")
	public List<RecentVisit> listNoPage(HttpServletRequest request,HttpServletResponse response,RecentVisit recentVisit){
		List<RecentVisit> lists = recentVisitService.findLateList(recentVisit);
		return lists;
	}
	
	/**
	 * 根据id查出数据库单条RecentVisit数据，每次请求都会先执行此方法，后在执行请求方法
	 * @param recentVisit
	 * @param map
	 */
	@ModelAttribute
	public void findRecentVisit(RecentVisit recentVisit,Map<String,Object> map){
		if(StringUtils.isNotBlank(recentVisit.getId())){
			RecentVisit model = recentVisitService.get(recentVisit.getId());
			map.put("recentVisit",model);
		}
	}
	
	@RequestMapping(value="/save",method= RequestMethod.POST)
	public void save(HttpServletRequest request,HttpServletResponse response,RecentVisit recentVisit) throws IOException{
		int result = 0;
		String ip = request.getHeader("X-Forwarded-For");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        } 
		recentVisit.setIp(ip);
		if(recentVisit.getUserId()==null||"".equals(recentVisit.getUserId())){
			recentVisit.setUserId("-1");
		}
		try {
			recentVisitService.save(recentVisit);
			result = 1;
			LogUtils.saveLog(request, "添加最近访问数据");
		} catch (Exception e) {
			result = -1;
			e.printStackTrace();
		}
		response.getWriter().write(result);
	}
	
	@RequestMapping(value="/del",method= RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response,RecentVisit model) throws IOException{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		if(StringUtils.isNotBlank(model.getId())){
			try {
				recentVisitService.delete(model);
				result.put("status", 1);
				LogUtils.saveLog(request, "删除最近访问数据");
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
