package com.kingmed.dp.modules.caseLib.service;

import org.os.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.caseLib.entity.CaseLibSlide;
import com.kingmed.dp.modules.caseLib.dao.CaseLibSlideDAO;

/**
 * 
 * @author jack
 * @version 2016年4月7日
 */
@Service
@Transactional(readOnly = true)
public class CaseLibSlideService extends CrudService<CaseLibSlideDAO, CaseLibSlide> {
		
	@Autowired
	private CaseLibSlideDAO caseLibSlideDAO;
	
	@Override
	public CaseLibSlideDAO getDao() {
		return caseLibSlideDAO;
	}
}


