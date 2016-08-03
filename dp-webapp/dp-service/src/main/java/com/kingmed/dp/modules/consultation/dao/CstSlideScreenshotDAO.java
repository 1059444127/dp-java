package com.kingmed.dp.modules.consultation.dao;

import java.util.List;

import org.os.common.persistence.CrudDAO;
import org.os.common.persistence.annotation.MyBatisDao;

import com.kingmed.dp.module.consultation.entity.CstSlideScreenshot;

/**
 * 
 * @author jack
 * @version 2016年3月29日
 */
@MyBatisDao
public interface CstSlideScreenshotDAO extends CrudDAO<CstSlideScreenshot> {
	
	int deleteByCaseId(CstSlideScreenshot entity);
	
	public List<CstSlideScreenshot> getImgs(String caseId);
}


