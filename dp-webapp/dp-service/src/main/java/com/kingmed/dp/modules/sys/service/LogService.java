package com.kingmed.dp.modules.sys.service;

import org.os.common.persistence.Page;
import org.os.common.service.BaseService;
import org.os.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.sys.entity.Log;
import com.kingmed.dp.module.sys.entity.User;
import com.kingmed.dp.module.sys.utils.UserUtils;
import com.kingmed.dp.modules.sys.dao.LogDAO;

/**
 * 
 * @author jack
 * @version 2016年4月7日
 */
@Service
@Transactional(readOnly = true)
public class LogService extends CrudService<LogDAO, Log> {
	
	@Autowired
	private LogDAO logDAO;
	
	@Override
	public LogDAO getDao() {
		return logDAO;
	}

	/**
	 * 根据条件返回日志列表
	 * @param log
	 * @return
	 */
	public Page<Log> findList(Page<Log> page,Log log) {
		Page<Log> list = null;
		User user = UserUtils.getUser();
		if (user.isAdmin()){
			list = super.findPage(page, log);
		}else{
			log.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "o", "u"));
			list = super.findPage(page, log);
		}
		return list;
	}
}


