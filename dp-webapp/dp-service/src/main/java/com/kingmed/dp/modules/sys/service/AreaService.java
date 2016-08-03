package com.kingmed.dp.modules.sys.service;

import java.util.List;

import org.os.common.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.sys.entity.Area;
import com.kingmed.dp.module.sys.utils.UserUtils;
import com.kingmed.dp.modules.sys.dao.AreaDAO;

/**
 * 
 * @author jack
 * @version 2016年4月7日
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDAO, Area> {
		
	@Autowired
	private AreaDAO areaDAO;
	
	@Override
	public AreaDAO getDao() {
		return areaDAO;
	}

	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
}


