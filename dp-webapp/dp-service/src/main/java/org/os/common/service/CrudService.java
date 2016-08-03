package org.os.common.service;

import java.util.List;

import org.os.common.persistence.CrudDAO;
import org.os.common.persistence.DataEntity;
import org.os.common.persistence.Page;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author jack
 * @version 2016年4月7日
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDAO<T>, T extends DataEntity<T>> extends BaseService {
	
	/**
	 * 持久层对象
	 */
	public abstract D getDao();
		
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return getDao().get(id);
	}
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return getDao().get(entity);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return getDao().findList(entity);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity) {
		page.setCount(getDao().findList(entity).size());
		entity.setPage(page);
		page.setList(getDao().findList(entity));
		return page;
	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			getDao().insert(entity);
		}else{
			entity.preUpdate();
			getDao().update(entity);
		}
	}
	
	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		getDao().delete(entity);
	}
}


