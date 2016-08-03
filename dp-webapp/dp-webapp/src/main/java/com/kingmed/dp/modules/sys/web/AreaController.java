package com.kingmed.dp.modules.sys.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.os.common.mapper.JsonMapper;
import org.os.common.persistence.Page;
import org.os.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.kingmed.dp.module.sys.entity.Area;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.utils.OfficeUtils;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.module.sys.vo.TreeNode;
import com.kingmed.dp.modules.sys.service.AreaService;

@Controller
@RequestMapping("admin/area")
public class AreaController  extends BaseController{

	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/list",method= {RequestMethod.GET,RequestMethod.POST})
	public String list(HttpServletRequest request,HttpServletResponse response){
		return "modules/sys/area_list";
	}
	
	@ResponseBody
	@RequestMapping(value="/searchList",method= {RequestMethod.GET,RequestMethod.POST})
	public ResponseVO<Area> searchList(HttpServletRequest request,HttpServletResponse response,Area area){
		Page<Area> pages = areaService.findPage(new Page(request,response), area);
		return new ResponseVO<Area>(pages);
	}
	
	@ResponseBody
	@RequestMapping(value="/tree",method=RequestMethod.GET)
	public List<TreeNode> queryTree(HttpServletRequest request,HttpServletResponse response){
		List<TreeNode> areaTrees = OfficeUtils.queryOfficeTree(areaService.findAll());
		return areaTrees;
	}
	
	@RequestMapping(value="/update",method= RequestMethod.GET)
	public void update(HttpServletRequest request,HttpServletResponse response,Area area){
		try {
			String jsonStr = JSON.toJSONString(area);
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
	public void findDict(Area area,Map<String,Object> map){
		if(StringUtils.isNotBlank(area.getId())){
			Area model = areaService.get(area.getId());
			map.put("area",model);
		}
	}
	
	@RequestMapping(value="/update",method= RequestMethod.POST)
	public void doUpdate(HttpServletRequest request,HttpServletResponse response,Area area){
		String titles = "添加区域";
		if(area.getId()!=null&&!"".equals(area.getId())){
			titles = "区域更新";
		}
		areaService.save(area);
		LogUtils.saveLog(request, titles);
	}
	
	@RequestMapping(value="/del",method= RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response,Area model) throws IOException{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		if(StringUtils.isNotBlank(model.getId())){
			try {
				areaService.delete(model);
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
