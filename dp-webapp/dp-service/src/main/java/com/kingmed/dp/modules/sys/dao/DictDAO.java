package com.kingmed.dp.modules.sys.dao;

import java.util.List;

import org.os.common.persistence.CrudDAO;
import org.os.common.persistence.annotation.MyBatisDao;

import com.kingmed.dp.module.sys.entity.Dict;

/**
 * 
 * @author jack
 * @version 2016年3月29日
 */
@MyBatisDao
public interface DictDAO extends CrudDAO<Dict> {
	public List<String> findTypeList(Dict dict);
}


