package com.kingmed.dp.modules.caseLib.service;

import java.util.List;

import org.os.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.caseLib.entity.CaseLib;
import com.kingmed.dp.module.caseLib.utils.CaseUtils;
import com.kingmed.dp.modules.caseLib.dao.CaseLibDAO;

/**
 * 
 * @author jack
 * @version 2016年4月7日
 */
@Service
@Transactional(readOnly = true)
public class CaseLibService extends CrudService<CaseLibDAO, CaseLib> {
		
	@Autowired
	private CaseLibDAO caseLibDAO;
	
	@Override
	public CaseLibDAO getDao() {
		return caseLibDAO;
	}
	/**
	 * 
	 * @return 1 是列表  -1 是缩略图(非1)
	 */
	public String getType(){
		return CaseUtils.getType();
	}
	
	public void setType(String type){
		CaseUtils.setType(type);
	}
	
	public List<CaseLib> findAll(){
		return CaseUtils.getCaseLibList();
	}

	@Transactional(readOnly = false)
	public void save(CaseLib caseLib) {
		super.save(caseLib);
		CaseUtils.removeCache(CaseUtils.CACHE_CASE_LIB_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(CaseLib caseLib) {
		super.delete(caseLib);
		CaseUtils.removeCache(CaseUtils.CACHE_CASE_LIB_LIST);
	}
}


