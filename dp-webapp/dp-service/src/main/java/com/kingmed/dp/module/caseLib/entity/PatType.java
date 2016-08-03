package com.kingmed.dp.module.caseLib.entity;

import org.os.common.persistence.TreeEntity;

/******************************************************************
** 类    名：PatType
** 描    述：病例库-分类
** 创 建 者：bianj
** 创建时间：2016-05-27 11:09:20
******************************************************************/

/**
 * 病例库-分类(T_PAT_TYPE)
 * 
 * @author bianj
 * @version 1.0.0 2016-05-27
 */
public class PatType extends TreeEntity<PatType> {
    /** 版本号 */
    private static final long serialVersionUID = -6373174332716182515L;
    
    /** 名称 */
    private String name;
    
    /** 代码 */
    private String code;
    
    /** 类型 */
    private String type;
    
    /** 病理病例库等级 */
    private String grade;
    
    
    public PatType() {
		super();
	}
    public PatType(String id) {
		super(id);
	}

	/**
     * 获取名称
     * 
     * @return 名称
     */
    public String getName() {
        return this.name;
    }
     
    /**
     * 设置名称
     * 
     * @param name
     *          名称
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 获取代码
     * 
     * @return 代码
     */
    public String getCode() {
        return this.code;
    }
     
    /**
     * 设置代码
     * 
     * @param code
     *          代码
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * 获取类型
     * 
     * @return 类型
     */
    public String getType() {
        return this.type;
    }
     
    /**
     * 设置类型
     * 
     * @param type
     *          类型
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * 获取病理病例库等级
     * 
     * @return 病理病例库等级
     */
    public String getGrade() {
        return this.grade;
    }
     
    /**
     * 设置病理病例库等级
     * 
     * @param grade
     *          病理病例库等级
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

	@Override
	public PatType getParent() {
		return parent;
	}

	@Override
	public void setParent(PatType parent) {
		this.parent = parent;
	}
	
	@Override
	public String toString() {
		return name;
	}
}