package com.kingmed.dp.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.os.common.persistence.CrudDAO;
import org.os.common.persistence.annotation.MyBatisDao;

import com.kingmed.dp.module.sys.entity.JobIsParams;

/**
 * 
 * @author 
 * @version 2016年5月6日
 */
@MyBatisDao
public interface JobIsParamsDAO extends CrudDAO<JobIsParams>{
	
	public List<Map<String,Object>> findAllList();
	
}
