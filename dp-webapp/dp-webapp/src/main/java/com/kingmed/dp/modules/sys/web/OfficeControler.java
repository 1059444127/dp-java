package com.kingmed.dp.modules.sys.web;

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
import com.kingmed.dp.module.sys.entity.Office;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.utils.OfficeUtils;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.module.sys.vo.TreeNode;
import com.kingmed.dp.modules.sys.service.OfficeService;

@Controller
@RequestMapping("admin/office")
public class OfficeControler extends BaseController{

	@Autowired
	private OfficeService officeService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(HttpServletRequest request,HttpServletResponse response){
		return "modules/sys/office_list";
	}
	
	@ResponseBody
	@RequestMapping(value="/searchList",method= {RequestMethod.GET,RequestMethod.POST})
	public ResponseVO<Office> searchList(HttpServletRequest request,HttpServletResponse response,Office office){
		Page<Office> pages = officeService.findList(new Page<Office>(request,response), office);
		return new ResponseVO<Office>(pages);
	}
	
	@ResponseBody
	@RequestMapping(value="/tree",method=RequestMethod.GET)
	public List<TreeNode> queryTree(HttpServletRequest request,HttpServletResponse response){
		List<Office> list = officeService.findAll();
		List<TreeNode> trees = OfficeUtils.queryOfficeTree(list);
		return trees;
	}
	@RequestMapping(value="/update",method= RequestMethod.GET)
	public void update(HttpServletRequest request,HttpServletResponse response,Office office){
		try {
			String jsonStr = JSON.toJSONString(office);
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 根据id查出数据库单条Office数据，每次请求都会先执行此方法，后在执行请求方法
	 * @param Office
	 * @param map
	 */
	@ModelAttribute
	public void findOffice(Office office,Map<String,Object> map){
		if(StringUtils.isNotBlank(office.getId())){
			Office model = officeService.get(office.getId());
			map.put("office",model);
		}
	}
	
	@RequestMapping(value="/update",method= RequestMethod.POST)
	public void doUpdate(HttpServletRequest request,HttpServletResponse response,Office office){
		String titles = "添加机构";
		if(office.getId() != null && !"".equals(office.getId())){
			titles = "机构更新";
		}
		officeService.save(office);
		LogUtils.saveLog(request, titles);
	}
	
	@RequestMapping(value="/del",method= RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response,Office model) throws IOException{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		if(StringUtils.isNotBlank(model.getId())){
			try {
				officeService.delete(model);
				result.put("status", 1);
				LogUtils.saveLog(request, "删除机构");
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
