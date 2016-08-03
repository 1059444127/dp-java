package com.kingmed.dp.modules.sys.dao;

import java.util.List;

import org.os.common.persistence.CrudDAO;
import org.os.common.persistence.annotation.MyBatisDao;

import com.kingmed.dp.module.sys.entity.Log;

/**
 * 
 * @author jack
 * @version 2016年3月22日
 */
@MyBatisDao
public interface LogDAO extends CrudDAO<Log> {

	public List<Log> findList(Log log);
	
	/*public List<Log> findAll(Log log);*/
}


