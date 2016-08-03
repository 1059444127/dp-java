package com.kingmed.dp.modules.consultation.service;

import org.os.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.consultation.entity.Scanner;
import com.kingmed.dp.modules.consultation.dao.ScannerDAO;

/**
* @author 作者 :
* @version 创建时间：2016年7月5日 上午10:19:36
* 类说明
*/
@Service
@Transactional(readOnly = true)
public class ScannerService extends CrudService<ScannerDAO,Scanner>{

	@Autowired
	private ScannerDAO scannerDAO;
	
	@Override
	public ScannerDAO getDao() {
		return scannerDAO;
	}
	
	public Scanner getByScannerCode(String scannerCode){
		return scannerDAO.getByScannerCode(scannerCode);
	}
}
