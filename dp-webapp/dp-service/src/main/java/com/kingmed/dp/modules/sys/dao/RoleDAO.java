package com.kingmed.dp.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.os.common.persistence.CrudDAO;
import org.os.common.persistence.annotation.MyBatisDao;

import com.kingmed.dp.module.sys.entity.Role;

/**
 * 
 * @author jack
 * @version 2016年3月22日
 */
@MyBatisDao
public interface RoleDAO extends CrudDAO<Role>{
	
	public Role getByName(Role role);

	public Role getByEnname(Role role);
	
	public List<Role> findPageList(Role role);
	
	public List<Map<String, Object>> getMenuByRoleId(String id);

	/**
	 * 维护角色与菜单权限关系
	 * @param role
	 * @return
	 */
	public int deleteRoleMenu(Role role);

	public int insertRoleMenu(Role role);

	/**
	 * 维护角色与公司部门关系
	 * @param role
	 * @return
	 */
	public int deleteRoleOffice(Role role);

	public int insertRoleOffice(Role role);
}
