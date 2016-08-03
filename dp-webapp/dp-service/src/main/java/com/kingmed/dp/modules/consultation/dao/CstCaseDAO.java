package com.kingmed.dp.modules.consultation.dao;

import java.util.List;
import java.util.Map;

import org.os.common.persistence.CrudDAO;
import org.os.common.persistence.annotation.MyBatisDao;

import com.kingmed.dp.module.consultation.entity.CstCase;
import com.kingmed.dp.module.consultation.entity.CstHelpCapture;
import com.kingmed.dp.module.consultation.entity.CstSlide;
import com.kingmed.dp.module.consultation.entity.CstSlideScreenshot;

/**
 * 
 * @author jack
 * @version 2016年3月29日
 */
@MyBatisDao
public interface CstCaseDAO extends CrudDAO<CstCase> {
	
	public Map<String, Object> get(Map<String, Object> map);
	
	public List<CstSlideScreenshot> getImgs(String caseId);
	
	public List<CstHelpCapture> getImgsHelp(String caseId);
	
	public String getAppValue(Object appKey);
	
	public List<CstSlide> getCstSlide(Object caseId);
}


