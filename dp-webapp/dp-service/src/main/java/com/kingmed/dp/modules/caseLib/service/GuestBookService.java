package com.kingmed.dp.modules.caseLib.service;

import org.os.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.caseLib.entity.GuestBook;
import com.kingmed.dp.modules.caseLib.dao.GuestBookDAO;

/**
* @author 作者 :zl
* @version 创建时间：2016年5月27日 上午10:19:36
* 类说明
*/
@Service
@Transactional(readOnly = true)
public class GuestBookService extends CrudService<GuestBookDAO,GuestBook>{

	@Autowired
	private GuestBookDAO guestBookDAO;
	
	@Override
	public GuestBookDAO getDao() {
		return guestBookDAO;
	}
	
}
