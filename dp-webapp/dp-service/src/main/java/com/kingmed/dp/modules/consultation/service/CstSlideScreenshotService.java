package com.kingmed.dp.modules.consultation.service;

import java.util.List;

import org.os.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.consultation.entity.CstSlideScreenshot;
import com.kingmed.dp.modules.consultation.dao.CstSlideScreenshotDAO;


/**
* @author 作者 :
* @version 创建时间：2016年5月27日 上午10:19:36
* 类说明
*/
@Service
@Transactional(readOnly = true)
public class CstSlideScreenshotService extends CrudService<CstSlideScreenshotDAO,CstSlideScreenshot>{

	@Autowired
	private CstSlideScreenshotDAO cstSlideScreenshotDAO;
	
	@Override
	public CstSlideScreenshotDAO getDao() {
		return cstSlideScreenshotDAO;
	}
	/**
	 * 删除
	 * @param caseId
	 */
	@Transactional(readOnly = false)
	public int deleteByCaseId(CstSlideScreenshot entity){
		return cstSlideScreenshotDAO.deleteByCaseId(entity);
	}
	
	public List<CstSlideScreenshot> getImgs(String caseId){
		return cstSlideScreenshotDAO.getImgs(caseId);
	}

}
