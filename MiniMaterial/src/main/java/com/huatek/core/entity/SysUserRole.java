package com.huatek.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
  * @ClassName: SysUserRole
  * @FullClassPath: com.frame.core.entity.SysUserRole
  * @Description: 用户与角色对应关系
  * @author: Arno
  * @date: Sep 11, 2017 4:18:06 PM
  * @version: 1.0
  */

@Entity
@Table(name = "sys_user_role")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysUserRole extends BaseEntity {
	
	
	/** @Fields serialVersionUID : */ 
	private static final long serialVersionUID = -3802378858787478287L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	/**
	 * 用户ID
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 角色ID
	 */
	@Column(name = "role_id")
	private Long roleId;

	/**
	 * 
	 */
	public SysUserRole() {
	}
	
	
	/**
	 * @param userId
	 * @param roleId
	 */
	public SysUserRole(Long userId, Long roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}


	/**
	 * 设置：
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取：
	 * @return Long
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 设置：用户ID
	 * @param userId 用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取：用户ID
	 * @return Long
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * 设置：角色ID
	 * @param roleId 角色ID
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取：角色ID
	 * @return Long
	 */
	public Long getRoleId() {
		return roleId;
	}
	
}
