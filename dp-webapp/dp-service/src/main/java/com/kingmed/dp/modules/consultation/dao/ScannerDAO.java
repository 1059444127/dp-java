package com.kingmed.dp.modules.consultation.dao;

import org.os.common.persistence.CrudDAO;
import org.os.common.persistence.annotation.MyBatisDao;

import com.kingmed.dp.module.consultation.entity.Scanner;

/**
 * 
 * @author jack
 * @version 2016年3月29日
 */
@MyBatisDao
public interface ScannerDAO extends CrudDAO<Scanner> {
	
	Scanner getByScannerCode(String scannerCode);
}


