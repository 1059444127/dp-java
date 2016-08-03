package com.kingmed.dp.modules.consultation.service;

import org.os.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.consultation.entity.CstSlide;
import com.kingmed.dp.modules.consultation.dao.CstSlideDAO;

/**
* @author 作者 :
* @version 创建时间：2016年5月27日 上午10:19:36
* 类说明
*/
@Service
@Transactional(readOnly = true)
public class CstSlideService extends CrudService<CstSlideDAO,CstSlide>{

	@Autowired
	private CstSlideDAO cstSlideDAO;
	
	@Override
	public CstSlideDAO getDao() {
		return cstSlideDAO;
	}

}
