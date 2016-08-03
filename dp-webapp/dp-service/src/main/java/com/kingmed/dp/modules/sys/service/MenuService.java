package com.kingmed.dp.modules.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.os.common.persistence.BaseEntity;
import org.os.common.persistence.Page;
import org.os.common.service.CrudService;
import org.os.common.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.sys.entity.Menu;
import com.kingmed.dp.module.sys.entity.User;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.utils.UserUtils;
import com.kingmed.dp.module.sys.vo.MenuVO;
import com.kingmed.dp.modules.sys.dao.MenuDAO;
import com.kingmed.dp.modules.sys.enums.MenuEnum;

@Service
@Transactional(readOnly = false)
public class MenuService extends CrudService<MenuDAO, Menu>{

	@Autowired
	private MenuDAO menuDAO;
	
	@Override
	public MenuDAO getDao() {
		return menuDAO;
	}

	@Override
	public Menu get(String id) {
		return menuDAO.get(id);
	}
	
	/**
	 * 查询菜单列表
	 * @return
	 */
	public List<Menu> findAll(){
		List<Menu> list = null;
		User user = UserUtils.getUser();
		if (user.isAdmin()){
			list = menuDAO.findAllList(new Menu());
		}else{
			Menu m = new Menu();
			m.setUserId(user.getId());
			list = menuDAO.findByUserId(m);
		}
		return list;
	}
	
	/**
	 * 
	 * 查询所有未删除的menu
	 * @return
	 */
	public List<Menu> findAllList() {
		List<Menu> list = findAll();
		return sortMenuList(list);
//		return list;
	}
	
	/**
	 * 获得左侧菜单栏原始数据
	 * @return
	 */
	public Map<String, Object> getMenuData() {
		Map<String, Object> map = new HashMap<>();
//		try {
		//获取菜单列表
		List<Menu> list = findAll(); //UserUtils.getMenuList();//menuDAO.findAllList();
		
		Menu menu = menuDAO.getRootMenu();
		list.remove(menu);
		MenuVO menuVO =  getMenuVO(list, menu);
		map.put("data", menuVO);
		map.put("success", true);
		return map;
//		} catch(Exception e) {
//			e.printStackTrace();
//			return null;
//		}
	}
	
	/**
	 * 查询当前id的menu属性，map格式返回
	 * @param id
	 * @return
	 */
	public Map<String, Object> getMapMenu(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Menu menu = menuDAO.get(id);
		if(menu.getParent().getId().equals(MenuEnum.NUMBER_ONE.getValue())) {
			map.put("parentName", MenuEnum.ROOT_MENU.getValue());
			map.put("parentId", MenuEnum.NUMBER_ONE.getValue());
		} else {
			map.put("parentId", menu.getParent().getId());
			if(menu.getParent() != null && StringUtils.isNoneBlank(menu.getParent().getName())) {
				map.put("parentName", menu.getParent().getName());
			} else {
				Menu parentMenu = get(menu.getParent().getId());
				map.put("parentName", parentMenu.getName());
			}
		}
		map.put("data", menu);
		return map;
	}
	
	@Override
	public Menu get(Menu entity) {
		return menuDAO.get(entity);
	}

	@Override
	public List<Menu> findList(Menu entity) {
		return super.findList(entity);
	}

	@Override
	public Page<Menu> findPage(Page<Menu> page, Menu entity) {
		return super.findPage(page, entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Menu entity) {
		
		super.save(entity);
		
		// 清除用户菜单缓存
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
		// 清除日志相关缓存
		CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}
	
	/**
	 * 保存菜单
	 * @param entity
	 * @return
	 */
	public Map<String, Object> saveMenu(Menu entity, String parentId) {
		//添加父id
		if(StringUtils.isBlank(parentId) || parentId.equals(MenuEnum.NUMBER_ONE.getValue())) {
			Menu menuParent = new Menu();
			menuParent.setId(MenuEnum.NUMBER_ONE.getValue());
			entity.setParent(menuParent);
			entity.setParentIds(MenuEnum.NUMBER_ONE.getValue()+MenuEnum.PARENT_IDS_SEPARATOR.getValue());
		} else if(StringUtils.isBlank(entity.getId())){
			Menu parent = get(parentId);
			parent.setId(parentId);
			entity.setParent(parent);
			String parentIds = parent.getParentIds() + parentId + MenuEnum.PARENT_IDS_SEPARATOR.getValue();
			entity.setParentIds(parentIds);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(entity.getId())) {
				entity = copyMenu(entity);
			}
			
			//验证同一个父菜单下是否存在同名菜单
			Menu menu = menuDAO.findByNameParentId(entity);
			if(menu != null && (StringUtils.isEmpty(entity.getId()) || 
					!entity.getId().equals(menu.getId()))) {
				map.put("success", false);
				map.put("message", "保存失败，已存在同名菜单。");
			} else {
			
				save(entity);
				map.put("success", true);
				map.put("message", "保存成功。");
			}
			//UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
		} catch(Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("message", "保存失败！");
		}
		return map;
	}
	
	/**
	 * 将修改后的信息写入到原menu中
	 * @param entity
	 * @return 修改后的menu
	 */
	private Menu copyMenu(Menu entity) {
		Menu menu = get(entity.getId());
		menu.setName(entity.getName());
		menu.setIcon(entity.getIcon());
		menu.setSort(entity.getSort());
		menu.setHref(entity.getHref());
		menu.setTarget(entity.getTarget());
		menu.setIsShow(entity.getIsShow());
		menu.setPermission(entity.getPermission());
		menu.setRemarks(entity.getRemarks());
//		menu.setParent(entity.getParent());
		return menu;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Menu entity) {
		menuDAO.delete(entity);
		// 清除用户菜单缓存
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
		// 清除日志相关缓存
		CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}
	
	/**
	 * 删除菜单
	 * @param entity
	 * @return
	 */
	public Map<String, Object> delMenu(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Menu entity = get(id);
			entity.setDelFlag(BaseEntity.DEL_FLAG_DELETE);
			delete(entity);
			map.put("success", true);
			map.put("message", "删除成功。");
		} catch(Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("message", "删除失败！");
		}
		return map;
	}
	
	/**
	 * 获取左侧菜单导航栏数据
	 * @param list 菜单列表
	 * @param menu 最顶级菜单
	 * @return
	 */
	private MenuVO getMenuVO(List<Menu> list, Menu menu) {
		MenuVO menuVo = new MenuVO();
		menuVo.setMenu(menu);
		List<MenuVO> child = new ArrayList<>();
		List<Integer> indexs = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			Menu menuC = list.get(i);
			if(menu.getId().equals(menuC.getParentId())) {
				MenuVO menuVoC = new MenuVO();
				menuVoC.setMenu(menuC);
				child.add(menuVoC);
				indexs.add(i);
			}
		}
		if(child.size() > 0) {
			menuVo.setChildList(child);
			int size = indexs.size();
			for(int i=size-1; i>0; i--) {
				int index = indexs.get(i);
				list.remove(index);
			}
			return getMenuVO(list, menuVo);
		}
		indexs.clear();
		indexs = null;
		return menuVo;
	}

	/**
	 * 遍历子菜单
	 * @param list
	 * @param menuVo
	 * @return
	 */
	private MenuVO getMenuVO(List<Menu> list, MenuVO menuVo) {
		List<MenuVO> child = menuVo.getChildList();
		for(int i=0; i<child.size(); i++) {
			MenuVO menuVoC = child.get(i);
			child.set(i, getMenuVO(list, menuVoC.getMenu()));
		}
		return menuVo;
	}
	
	/**
	 * 对菜单列表筛选并返回新的有序列表，sort升序，排序方式为：父-子...-子...，父-子...-子...,...
	 * @param originallLis
	 * @param menu 父菜单
	 * @return
	 */
	private List<Menu> sortMenuList(List<Menu> originalList) {
		List<Menu> listSon = new ArrayList<>(); 
		Menu menu = originalList.get(0);
		originalList.remove(0);

		listSon.add(menu);
		if(originalList.size() > 0) {
			listSon.addAll(getMenuSon(originalList, menu));
		}
		//如果原始列表还有数据则继续
		if(originalList.size() > 0){
			listSon.addAll(sortMenuList(originalList));
		}
		return listSon;
	}
	
	/**
	 * 查询所给菜单的所有子菜单
	 * @param fatherList
	 * @param menuFather
	 * @return
	 */
   private List<Menu> getMenuSon(List<Menu> fatherList, Menu menuFather) {
	  	List<Menu> list = new ArrayList<>();
	  	List<Menu> listSon = new ArrayList<Menu>();
	  	List<Integer> listIndex = new ArrayList<Integer>();
	  	int size = fatherList.size();
	  	//查找所给菜单的所有子菜单
	  	for(int i=0; i<size; i++) {
	  		Menu menu = fatherList.get(i);
	  		if(menu.getParent().getId().equals(menuFather.getId())) {
	  			listIndex.add(i);
	  			list.add(menu);
	  		}
	  	}
	  	
	  	size = list.size();
	  	
	  	if(size > 0) {
		  	//将查询到的菜单从原始列表删除
		  	for(int i=(listIndex.size()-1); i>=0; i--) {
		  		fatherList.remove((int)listIndex.get(i));
		  	}
		  	listIndex.clear();
		  	listIndex = null;
	  		for(int i=0; i<size; i++) {
 				Menu son = list.get(i);
	  			listSon.add(son);
	  			if(fatherList.size() > 0) {
	  				listSon.addAll(getMenuSon(fatherList, son));
	  			} 
	  		}
	  		list.clear();
	  		list = null;
	  	}
	  	
	  	return listSon;
	}
	
}
