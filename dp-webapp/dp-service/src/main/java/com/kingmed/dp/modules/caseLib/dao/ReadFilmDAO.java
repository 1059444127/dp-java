package com.kingmed.dp.modules.caseLib.dao;

import java.util.List;

import org.os.common.persistence.CrudDAO;
import org.os.common.persistence.annotation.MyBatisDao;

import com.kingmed.dp.module.caseLib.entity.ReadFilm;

/**
 * 
 * @author jack
 * @version 2016年3月22日
 */
@MyBatisDao
public interface ReadFilmDAO  extends CrudDAO<ReadFilm>{

	List<ReadFilm> findTreeList(ReadFilm readFilm);
}
