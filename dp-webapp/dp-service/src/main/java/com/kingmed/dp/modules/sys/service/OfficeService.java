package com.kingmed.dp.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.os.common.persistence.Page;
import org.os.common.service.BaseService;
import org.os.common.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.sys.entity.Office;
import com.kingmed.dp.module.sys.entity.User;
import com.kingmed.dp.module.sys.utils.UserUtils;
import com.kingmed.dp.modules.sys.dao.OfficeDAO;

/**
 * 
 * @author jack
 * @version 2016年4月7日
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDAO, Office> {
	
	@Autowired
	private OfficeDAO officeDAO;
	
	@Override
	public OfficeDAO getDao() {
		return officeDAO;
	}

	public List<Office> findAll(){
		return UserUtils.getOfficeList();
	}
	
	/**
	 * 分页查询
	 * @param page
	 * @param office
	 * @return
	 */
	public Page<Office> findList(Page<Office> page,Office office){
		Page<Office> list = null;
		User user = UserUtils.getUser();
		if (user.isAdmin()){
			list = super.findPage(page, office);
		}else{
			office.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "p", ""));
			list = super.findPage(page, office);
		}
		return list;
	}
	
	public List<Office> findList(Boolean isAll){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList();
		}else{
			return UserUtils.getOfficeList();
		}
	}
	
	@Transactional(readOnly = true)
	public List<Office> findList(Office office){
		if(office != null){
			office.setParentIds(office.getParentIds()+"%");
			return getDao().findByParentIdsLike(office);
		}
		return  new ArrayList<Office>();
	}
	
	@Transactional(readOnly = false)
	public void save(Office office) {
		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Office office) {
		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
}


