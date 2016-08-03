package com.kingmed.dp.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingmed.dp.module.sys.entity.Menu;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.utils.OfficeUtils;
import com.kingmed.dp.module.sys.utils.UserUtils;
import com.kingmed.dp.module.sys.vo.TreeNode;
import com.kingmed.dp.modules.sys.service.MenuService;

@Controller
@RequestMapping(value="/admin/menu")
public class MenuController {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 加载左侧边栏数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getMenuList(Model model) {
//		List<Menu> list = menuService.findAllList();
//		return list;
		logger.info("search:/admin/menu/list,return:Map<String, Object>");
		Map<String, Object> map = menuService.getMenuData();
		return map;
	}
	
	/**
	 * 测试页面
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test(Model model,HttpServletRequest request, String id) {
		return "test/sidebar";
	}
	
	/**
	 * 菜单管理
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/manage", method=RequestMethod.GET)
	public String manage(Model model,HttpServletRequest request, String id) {
//		List<Menu> list = menuService.findAllList();
//		request.setAttribute("menuList", list);
		return "modules/sys/manageMenu";
	}
	
	/**
	 * 根据前段现有条件
	 * @param model
	 * @param request
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="parameterList", method=RequestMethod.POST)
	public @ResponseBody List<Menu> parameterList(Model model,HttpServletRequest request, String parentId) {

		logger.info("search:/admin/menu/parameterList,return:List<Menu>");
		List<Menu> list = null;
////		try {
			list = menuService.findAllList();
////		} catch(Exception e) {
////			e.printStackTrace();
////		}
		return list;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> edit(Model model, String id, 
			HttpServletRequest request) {
		logger.info("search:/admin/menu/edit,return:Map<String, Object>");
		Map<String, Object> map = menuService.getMapMenu(id);
		return map;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> save(Model model, Menu menu, 
			String parentId, HttpServletRequest request) {
		
		logger.info("search:/admin/menu/save,return:Map<String, Object>");
		String title = StringUtils.isEmpty(menu.getId()) ? "新增菜单":"编辑菜单";
		
		Map<String, Object> map = menuService.saveMenu(menu, parentId);
		
		if(Boolean.parseBoolean(map.get("success").toString())) {
			LogUtils.saveLog(request, title);
		}
		
		return map;
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> del(Model model, String id, 
			HttpServletRequest request) {
		logger.info("search:/admin/menu/del,return:Map<String, Object>");
		Map<String, Object> map = menuService.delMenu(id);
		if(Boolean.parseBoolean(map.get("success").toString())) {
			LogUtils.saveLog(request, "删除菜单");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/tree",method=RequestMethod.GET)
	public List<TreeNode> queryTree(HttpServletRequest request,HttpServletResponse response){
		List<Menu> list = UserUtils.getMenuList();
		List<TreeNode> trees = OfficeUtils.queryOfficeTree(list);
		return trees;
	}

}
