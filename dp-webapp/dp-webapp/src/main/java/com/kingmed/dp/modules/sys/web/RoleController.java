package com.kingmed.dp.modules.sys.web;

import java.io.IOException;
import java.util.ArrayList;
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
import com.kingmed.dp.module.sys.entity.Role;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.modules.sys.service.SystemService;

/**
* @author 作者 :zl
* @version 创建时间：2016年4月27日 下午2:23:22
* 类说明
*/
@Controller
@RequestMapping("admin/role")
public class RoleController extends BaseController {

	@Autowired
	private SystemService roleService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(HttpServletRequest request,Map<String, Object> map){
		return "modules/sys/role_list";
	}
	
	@ResponseBody
	@RequestMapping(value="/searchList",method= {RequestMethod.GET,RequestMethod.POST})
	public ResponseVO<Role> searchList(HttpServletRequest request,HttpServletResponse response,Role role){
		Page<Role> pages =roleService.findRole(new Page(request,response), role);
		return new ResponseVO<Role>(pages);
	}
	
	@RequestMapping(value="/update",method= RequestMethod.GET)
	public void update(HttpServletRequest request,HttpServletResponse response,Role role){
		try {
			String jsonStr = JSON.toJSONString(role);
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/updateMenu",method= RequestMethod.GET)
	public List<Map<String, Object>> updateMenu(HttpServletRequest request,HttpServletResponse response,Role role){
		List<Map<String, Object>> menus = roleService.getMenuByRoleId(role.getId());
		return menus;
	}
	@RequestMapping(value="/updateMenu",method= RequestMethod.POST)
	public void doUpdateMenu(HttpServletRequest request,HttpServletResponse response,Role role){
		roleService.updateRoleMenu(role);
		LogUtils.saveLog(request, "分配菜单");
	}
	
	/**
	 * 根据id查出数据库单条Office数据,每次请求都会先执行此方法，后在执行请求方法
	 * @param Office
	 * @param map
	 */
	@ModelAttribute
	public void findOffice(Role role,Map<String,Object> map){
		if(StringUtils.isNotBlank(role.getId())){
			Role model = roleService.getRole(role.getId());
			map.put("role",model);
		}
	}
	
	@RequestMapping(value="/update",method= RequestMethod.POST)
	public void doUpdate(HttpServletRequest request,HttpServletResponse response,Role role){
		String titles = "添加角色";
		if(role.getId()!=null&&!"".equals(role.getId())){
			titles = "角色更新";
		}
		List<Office> offices = new ArrayList<Office>();
		offices.add(role.getOffice());
		role.setOfficeList(offices);
		roleService.saveRole(role);
		
		LogUtils.saveLog(request, titles);
	}
	
	@RequestMapping(value="/del",method= RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response,Role model) throws IOException{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		if(StringUtils.isNotBlank(model.getId())){
			try {
				roleService.deleteRole(model);
				result.put("status", 1);
				LogUtils.saveLog(request, "角色删除");
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
