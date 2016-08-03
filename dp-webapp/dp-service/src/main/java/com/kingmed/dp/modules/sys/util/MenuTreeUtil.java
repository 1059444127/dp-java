package com.kingmed.dp.modules.sys.util;

import java.util.ArrayList;
import java.util.List;

import com.kingmed.dp.module.sys.entity.Menu;
import com.kingmed.dp.module.sys.vo.MenuVO;

public class MenuTreeUtil {
	
	/**
	 * 获取左侧菜单导航栏数据
	 * @param list 菜单列表
	 * @param menu 最顶级菜单
	 * @return
	 */
	public static MenuVO getMenuVO(List<Menu> list, Menu menu) {
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

	private static MenuVO getMenuVO(List<Menu> list, MenuVO menuVo) {
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
	public static List<Menu> sortMenuList(List<Menu> originalList) {
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
   private static List<Menu> getMenuSon(List<Menu> fatherList, Menu menuFather) {
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
