package com.huatek.core.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

/**
  * @ClassName: SysDept
  * @FullClassPath: com.frame.core.entity.SysDept
  * @Description: 部门管理
  * @author: Arno
  * @date: Sep 14, 2017 12:01:32 PM
  * @version: 1.0
  */

@Entity
@Table(name = "sys_dept")
@DynamicUpdate(true)
public class SysDept extends BaseEntity {
	
	/** @Fields serialVersionUID : */ 
	private static final long serialVersionUID = 8258578949736368416L;
	
	//部门ID
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dept_id")
	private Long deptId;
	
	//上级部门ID，一级部门为0
	@Column(name = "parent_id")
	private Long parentId;
	
	//部门名称
	@Column(name = "name")
	private String name;
	
	//部门类型
	@Column(name = "type")
	private String type;
		
	
	//排序
	@Column(name = "order_num")
	private Integer orderNum;
	
	/** @Fields remark : 备注*/ 
	@Column(name = "remark")
	private String remark;
	
	/** @Fields orgCode 部门编码: */ 
	@Column(name = "orgcode")
	private String orgCode;
	
	//上级部门名称
	@Transient
	private String parentName;
	
	//上级部门名称
	@Transient
	private String parentOrgCode;		
	
	//部门类型名称
	@Transient
	private String typeName;
		
	/**
	 * ztree属性
	 */
	@Transient
	private Boolean open;
	
	@Transient
	private List<?> list;

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getDeptId() {
		return deptId;
	}
	/**
	 * 设置：上级部门ID，一级部门为0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：上级部门ID，一级部门为0
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 设置：部门名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：部门名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：排序
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the orgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * @param orgCode the orgCode to set
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * @return the parentOrgCode
	 */
	public String getParentOrgCode() {
		return parentOrgCode;
	}

	/**
	 * @param parentOrgCode the parentOrgCode to set
	 */
	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}
}
