package com.kingmed.dp.module.sys.vo;

import java.util.List;

import com.kingmed.dp.module.sys.entity.Menu;

/**
 * 左侧菜单栏展示用menu数据
 * @author Administrator
 *
 */
public class MenuVO {

	private Menu menu;
	
	private boolean child;
	
	private List<MenuVO> childList;
	
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<MenuVO> getChildList() {
		return childList;
	}

	public void setChildList(List<MenuVO> childList) {
		this.childList = childList;
	}

	public boolean isChild() {
		if(childList != null && childList.size() > 0) {
			child = true;
		} else {
			child = false;
		}
		return child;
	}

	public void setChild(boolean child) {
		this.child = child;
	}
	
	
}
