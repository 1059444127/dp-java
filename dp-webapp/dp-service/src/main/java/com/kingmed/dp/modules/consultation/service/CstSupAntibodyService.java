package com.kingmed.dp.modules.consultation.service;

import org.os.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.consultation.entity.CstSupAntibody;
import com.kingmed.dp.modules.consultation.dao.CstSupAntibodyDAO;

/**
* @author 作者 :
* @version 创建时间：2016年5月27日 上午10:19:36
* 类说明
*/
@Service
@Transactional(readOnly = true)
public class CstSupAntibodyService extends CrudService<CstSupAntibodyDAO,CstSupAntibody>{

	@Autowired
	private CstSupAntibodyDAO cstSupAntibodyDAO;
	
	@Override
	public CstSupAntibodyDAO getDao() {
		return cstSupAntibodyDAO;
	}

}
