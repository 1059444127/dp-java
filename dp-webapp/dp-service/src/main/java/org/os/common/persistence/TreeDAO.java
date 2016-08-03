package org.os.common.persistence;

import java.util.List;

/**
 * 
 * @author jack
 * @version 2016年3月22日
 */
public interface TreeDAO<T extends TreeEntity<T>> extends CrudDAO<T> {
	/**
	 * 找到所有子节点
	 * @param entity
	 * @return
	 */
	public List<T> findByParentIdsLike(T entity);

	/**
	 * 更新所有父节点字段
	 * @param entity
	 * @return
	 */
	public int updateParentIds(T entity);
	
}


