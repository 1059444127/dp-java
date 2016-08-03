package com.kingmed.dp.modules.sys.web;

import java.io.IOException;
import java.util.ArrayList;
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
import com.kingmed.dp.module.sys.entity.Office;
import com.kingmed.dp.module.sys.entity.Role;
import com.kingmed.dp.module.sys.entity.User;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.utils.OfficeUtils;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.module.sys.vo.TreeNode;
import com.kingmed.dp.modules.sys.service.OfficeService;
import com.kingmed.dp.modules.sys.service.SystemService;

@Controller
@RequestMapping("admin/user")
public class UserController extends BaseController{

	@Autowired
	private SystemService userService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("roles", userService.findAllRole());
		return "modules/sys/user_list";
	}
	
	@ResponseBody
	@RequestMapping(value="/searchList",method= {RequestMethod.GET,RequestMethod.POST})
	public ResponseVO<User> searchList(HttpServletRequest request,HttpServletResponse response,User user){
		Page<User> pages = userService.findUser(new Page(request,response), user);
		return new ResponseVO<User>(pages);
	}
	
	@RequestMapping(value="/update",method= RequestMethod.GET)
	public void update(HttpServletRequest request,HttpServletResponse response,User user){
		try {
			String jsonStr = JSON.toJSONString(user);
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id查出数据库单条Office数据,每次请求都会先执行此方法，后在执行请求方法
	 * @param Office
	 * @param map
	 */
	@ModelAttribute
	public void findOffice(User user,Map<String,Object> map){
		if(StringUtils.isNotBlank(user.getId())){
			User model = userService.getUser(user.getId());
			map.put("user",model);
		}
	}
	
	@RequestMapping(value="/update",method= RequestMethod.POST)
	public void doUpdate(HttpServletRequest request,HttpServletResponse response,User user){
		String titles = "用户更新";
		if(!(user.getId() !=""&&!"".equals(user.getId()))&&user.getPassword()!=null&&!"".equals(user.getPassword())){
			user.setPassword(SystemService.entryptPassword(user.getPassword()));
			titles = "添加用户";
		}
		userService.saveUser(user);
		LogUtils.saveLog(request, titles);
	}
	
	@RequestMapping(value="/del",method= RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response,User model) throws IOException{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		if(StringUtils.isNotBlank(model.getId())){
			try {
				userService.deleteUser(model);
				result.put("status", 1);
				LogUtils.saveLog(request, "删除用户");
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
