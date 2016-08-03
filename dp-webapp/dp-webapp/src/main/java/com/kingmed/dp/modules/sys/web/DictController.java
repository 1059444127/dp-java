package com.kingmed.dp.modules.sys.web;

import java.io.IOException;
import java.util.HashMap;
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
import com.kingmed.dp.module.sys.entity.Dict;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.modules.sys.service.DictService;

@Controller
@RequestMapping("admin/dict")
public class DictController  extends BaseController{

	@Autowired
	private DictService dictService;
	
	@RequestMapping(value="/list",method= {RequestMethod.GET,RequestMethod.POST})
	public String list(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return "modules/sys/dict_list";
	}
	
	@ResponseBody
	@RequestMapping(value="/searchList",method= {RequestMethod.GET,RequestMethod.POST})
	public ResponseVO<Dict> searchList(HttpServletRequest request,HttpServletResponse response,Dict dict){
		Page<Dict> pages = dictService.findPage(new Page(request,response), dict);
		return new ResponseVO<Dict>(pages);
	}
	
	@RequestMapping(value="/update",method= RequestMethod.GET)
	public void update(HttpServletRequest request,HttpServletResponse response,Dict dict){
		try {
			String jsonStr = JSON.toJSONString(dict);
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
	public void findDict(Dict dict,Map<String,Object> map){
		if(StringUtils.isNotBlank(dict.getId())){
			Dict model = dictService.get(dict.getId());
			map.put("dict",model);
		}
	}
	
	@RequestMapping(value="/update",method= RequestMethod.POST)
	public void doUpdate(HttpServletRequest request,HttpServletResponse response,Dict dict){
		String titles = "添加字典";
		if(dict.getId()!=null&&!"".equals(dict.getId())){
			titles = "字典更新";
		}
		dictService.save(dict);
		LogUtils.saveLog(request, titles);
	}
	
	@RequestMapping(value="/del",method= RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response,Dict model) throws IOException{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		if(StringUtils.isNotBlank(model.getId())){
			try {
				dictService.delete(model);
				result.put("status", 1);
				LogUtils.saveLog(request, "删除字典");
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
