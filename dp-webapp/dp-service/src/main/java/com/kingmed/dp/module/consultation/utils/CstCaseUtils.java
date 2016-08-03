package com.kingmed.dp.module.consultation.utils;

import org.os.common.utils.SpringContextHolder;

import com.kingmed.dp.modules.consultation.dao.CstCaseDAO;

/**
* @author 作者 :
* @version 创建时间：2016年5月27日 上午10:14:54
* 类说明
*/
public class CstCaseUtils {

	private static CstCaseDAO cstCaseDAO = SpringContextHolder.getBean(CstCaseDAO.class);
	
	public static final String CACHE_CST_CASE_MAP = "cstCaseMap";
}
