package com.kingmed.dp.modules.caseLib.service;

import java.util.List;

import org.os.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.caseLib.entity.RecentVisit;
import com.kingmed.dp.modules.caseLib.dao.RecentVisitDAO;

/**
* @author 作者 :zl
* @version 创建时间：2016年5月27日 上午10:19:36
* 类说明
*/
@Service
@Transactional(readOnly = true)
public class RecentVisitService extends CrudService<RecentVisitDAO,RecentVisit>{

	@Autowired
	private RecentVisitDAO recentVisitDAO;
	
	@Override
	public RecentVisitDAO getDao() {
		return recentVisitDAO;
	}
	public List<RecentVisit> findLateList(RecentVisit model){
		return recentVisitDAO.findLateList(model);
	}
	
	public int findListCount(RecentVisit model){
		return recentVisitDAO.findListCount(model);
	}
}
