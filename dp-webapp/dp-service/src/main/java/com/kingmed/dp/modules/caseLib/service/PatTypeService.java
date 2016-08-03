package com.kingmed.dp.modules.caseLib.service;

import java.util.List;

import org.os.common.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.caseLib.entity.PatType;
import com.kingmed.dp.module.caseLib.utils.CaseUtils;
import com.kingmed.dp.modules.caseLib.dao.PatTypeDAO;

/**
 * 
 * @author jack
 * @version 2016年4月7日
 */
@Service
@Transactional(readOnly = true)
public class PatTypeService extends TreeService<PatTypeDAO, PatType> {
		
	@Autowired
	private PatTypeDAO patTypeDAO;
	
	@Override
	public PatTypeDAO getDao() {
		return patTypeDAO;
	}

	public List<PatType> findAll(){
		return CaseUtils.getPatTypeList();
	}

	@Transactional(readOnly = false)
	public void save(PatType patType) {
		super.save(patType);
		CaseUtils.removeCache(CaseUtils.CACHE_PAT_TYPE_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(PatType patType) {
		super.delete(patType);
		CaseUtils.removeCache(CaseUtils.CACHE_PAT_TYPE_LIST);
	}
}


