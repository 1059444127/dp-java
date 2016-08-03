package com.kingmed.dp.modules.sys.dao;

import org.os.common.persistence.TreeDAO;
import org.os.common.persistence.annotation.MyBatisDao;

import com.kingmed.dp.module.sys.entity.Office;

/**
 * 
 * @author jack
 * @version 2016年3月22日
 */
@MyBatisDao
public interface OfficeDAO extends TreeDAO<Office>{

}
