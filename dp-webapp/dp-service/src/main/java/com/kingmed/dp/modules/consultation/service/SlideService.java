package com.kingmed.dp.modules.consultation.service;

import org.os.common.service.CrudService;
import org.os.common.utils.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.consultation.entity.Slide;
import com.kingmed.dp.modules.consultation.dao.SlideDAO;

/**
* @author 作者 :zl
* @version 创建时间：2016年5月27日 上午10:19:36
* 类说明
*/
@Service
@Transactional(readOnly = true)
public class SlideService extends CrudService<SlideDAO,Slide>{

	@Autowired
	private SlideDAO slideDAO;
	
	@Override
	public SlideDAO getDao() {
		return slideDAO;
	}
	
}
