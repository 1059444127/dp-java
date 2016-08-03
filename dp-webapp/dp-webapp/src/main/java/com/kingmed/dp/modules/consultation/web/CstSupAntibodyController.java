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
import com.kingmed.dp.module.consultation.entity.CstSupAntibody;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.modules.consultation.service.CstSupAntibodyService;

@Controller
@RequestMapping("cons/cstSupAntibody")
public class CstSupAntibodyController  extends BaseController{

	@Autowired
	private CstSupAntibodyService cstSupAntibodyService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return "modules/consultation/cstSupAntibody_list";
	}
	
	@ResponseBody
	@RequestMapping("/searchList")
	public ResponseVO<CstSupAntibody> searchList(HttpServletRequest request,HttpServletResponse response,CstSupAntibody cstSupAntibody){
		Page<CstSupAntibody> pages = cstSupAntibodyService.findPage(new Page<CstSupAntibody>(request,response), cstSupAntibody);
		return new ResponseVO<CstSupAntibody>(pages);
	}
	@ResponseBody
	@RequestMapping("/listNoPage")
	public List<CstSupAntibody> listNoPage(HttpServletRequest request,HttpServletResponse response,CstSupAntibody cstSupAntibody){
		List<CstSupAntibody> lists = cstSupAntibodyService.findList(cstSupAntibody);
		return lists;
	}
	
	@RequestMapping(value="/update",method= RequestMethod.GET)
	public void update(HttpServletRequest request,HttpServletResponse response,CstSupAntibody cstSupAntibody){
		try {
			String jsonStr = JSON.toJSONString(cstSupAntibody);
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据id查出数据库单条CstSupAntibody数据，每次请求都会先执行此方法，后在执行请求方法
	 * @param cstSupAntibody
	 * @param map
	 */
	@ModelAttribute
	public void findCstSupAntibody(CstSupAntibody cstSupAntibody,Map<String,Object> map){
		if(StringUtils.isNotBlank(cstSupAntibody.getId())){
			CstSupAntibody model = cstSupAntibodyService.get(cstSupAntibody.getId());
			map.put("cstSupAntibody",model);
		}
	}
	
	@RequestMapping(value="/update",method= RequestMethod.POST)
	public void doUpdate(HttpServletRequest request,HttpServletResponse response,CstSupAntibody cstSupAntibody){
		String titles = "添加反馈意见";
		if(cstSupAntibody.getId()!=null&&!"".equals(cstSupAntibody.getId())){
			titles = "反馈意见更新";
		}
		cstSupAntibodyService.save(cstSupAntibody);
		LogUtils.saveLog(request, titles);
	}
	
	@RequestMapping(value="/del",method= RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response,CstSupAntibody model) throws IOException{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		if(StringUtils.isNotBlank(model.getId())){
			try {
				cstSupAntibodyService.delete(model);
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
