package com.kingmed.dp.modules.sys.service;

import java.util.List;

import org.os.common.service.CrudService;
import org.os.common.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.sys.entity.Dict;
import com.kingmed.dp.module.sys.utils.DictUtils;
import com.kingmed.dp.modules.sys.dao.DictDAO;

/**
 * 
 * @author jack
 * @version 2016年4月7日
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDAO, Dict> {
	
	@Autowired
	private DictDAO dictDAO;
	
	@Override
	public DictDAO getDao() {
		return dictDAO;
	}

	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return getDao().findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}
}


