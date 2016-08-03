package com.kingmed.dp.modules.sys.dao;

import java.util.List;

import org.os.common.persistence.CrudDAO;
import org.os.common.persistence.annotation.MyBatisDao;

import com.kingmed.dp.module.sys.entity.Menu;

/**
 * 
 * @author jack
 * @version 2016年3月22日
 */
@MyBatisDao
public interface MenuDAO extends CrudDAO<Menu>{
	
	public List<Menu> findByParentIdsLike(Menu menu);

	public List<Menu> findByUserId(Menu menu);
	
	public List<Menu> findAllList();
	
	/**
	 * 查询根节点菜单信息
	 * @return
	 */
	public Menu getRootMenu();
	
	/**
	 * 查询是否存在相同名字和父节点的菜单
	 * @param menu
	 * @return
	 */
	public Menu findByNameParentId(Menu menu);
	
	public int updateParentIds(Menu menu);
	
	public int updateSort(Menu menu);
	
	public void delete(String id);
}
