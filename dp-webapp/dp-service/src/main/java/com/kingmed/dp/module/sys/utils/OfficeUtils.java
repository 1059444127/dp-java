package com.kingmed.dp.module.sys.utils;

import java.util.ArrayList;
import java.util.List;

import org.os.common.persistence.BaseEntity;
import org.os.common.persistence.TreeEntity;

import com.kingmed.dp.module.caseLib.entity.ReadFilm;
import com.kingmed.dp.module.sys.entity.Area;
import com.kingmed.dp.module.sys.entity.Menu;
import com.kingmed.dp.module.sys.entity.Office;
import com.kingmed.dp.module.sys.vo.TreeNode;

/**
 * 
 * @author zl
 * @date   2016年4月15日下午4:15:53
 */
public class OfficeUtils {
	
	/**
	 * 将Office对象转换成TreeNode对象，方便前端生成树
	 * @param <T>
	 * @param offices
	 * @return  返回平级的TreeNode集合
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<TreeNode> queryOfficeTree(List<T> lists){
		List<TreeNode> nodes = new ArrayList<TreeNode>();
        TreeNode node = null;
        for(T object : lists){
        	node= new TreeNode();
            node.setId(((BaseEntity<T>) object).getId());
            if(object instanceof Office ){
            	node.setType(((Office)object).getType());
            	node.setName(((Office) object).getName());
                node.setpId(((Office) object).getParentId());
            }else if (object instanceof Area) {
            	node.setType(((Area)object).getCode());//树中的区域类型没有用到，设置成区域编码
            	node.setName(((Area) object).getName());
                node.setpId(((Area) object).getParentId());
            }else if(object instanceof Menu){
            	node.setName(((Menu) object).getName());
                node.setpId(((Menu) object).getParentId());
            }else if(object instanceof ReadFilm){
            	node.setName(((ReadFilm) object).getName());
            	node.setpId(((ReadFilm) object).getParentId());
            	//node.setUrl("cons/lib/readFilm/detail?id="+((BaseEntity<T>) object).getId());
            }else{
            	node.setName(((TreeEntity<T>) object).getName());
            	node.setpId(((TreeEntity<T>) object).getParentId());
            }
            node.setOpen(true);
            nodes.add(node);
        }
        return nodes;
	}
	
	/**
	 * 返回值是权限内所有公司集合
	 * @param offices
	 * @return
	 */
	public static List<Office> getCompanyAndDepartment(List<Office> offices){
		List<Office> companys = new ArrayList<Office>();
		for(Office model : offices){
			if("1".equals(model.getType())){
				companys.add(model);
			}
		}
		return companys;
	}
}


