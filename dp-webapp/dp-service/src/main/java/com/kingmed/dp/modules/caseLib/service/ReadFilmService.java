package com.kingmed.dp.modules.caseLib.service;

import java.util.List;

import org.os.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.caseLib.entity.ReadFilm;
import com.kingmed.dp.module.caseLib.utils.CaseUtils;
import com.kingmed.dp.modules.caseLib.dao.ReadFilmDAO;

/**
* @author 作者 :zl
* @version 创建时间：2016年5月27日 上午10:19:36
* 类说明
*/
@Service
@Transactional(readOnly = true)
public class ReadFilmService extends CrudService<ReadFilmDAO,ReadFilm>{

	@Autowired
	private ReadFilmDAO readFilmDAO;
	
	@Override
	public ReadFilmDAO getDao() {
		return readFilmDAO;
	}
	
	public List<ReadFilm> findAll(){
		return CaseUtils.getReadFilmList();
	}

	@Transactional(readOnly = false)
	public void save(ReadFilm readFilm) {
		super.save(readFilm);
		CaseUtils.removeCache(CaseUtils.CACHE_READ_FILM_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(ReadFilm readFilm) {
		super.delete(readFilm);
		CaseUtils.removeCache(CaseUtils.CACHE_READ_FILM_LIST);
	}
}
