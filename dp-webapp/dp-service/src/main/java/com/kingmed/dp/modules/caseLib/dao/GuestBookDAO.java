package com.kingmed.dp.modules.caseLib.dao;

import org.os.common.persistence.CrudDAO;
import org.os.common.persistence.annotation.MyBatisDao;

import com.kingmed.dp.module.caseLib.entity.GuestBook;

/**
 * 
 * @author jack
 * @version 2016年3月22日
 */
@MyBatisDao
public interface GuestBookDAO  extends CrudDAO<GuestBook>{

}
