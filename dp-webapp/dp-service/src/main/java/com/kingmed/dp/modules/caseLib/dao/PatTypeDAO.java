package com.kingmed.dp.modules.caseLib.dao;

import org.os.common.persistence.TreeDAO;
import org.os.common.persistence.annotation.MyBatisDao;

import com.kingmed.dp.module.caseLib.entity.PatType;

/**
 * 
 * @author jack
 * @version 2016年3月22日
 */
@MyBatisDao
public interface PatTypeDAO  extends TreeDAO<PatType>{

}
