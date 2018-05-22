package com.huatek.core.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huatek.core.entity.SysDept;


@Entity
@Table(name = "sys_user")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysUser extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** @Fields userId : 用户ID */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long userId;

	/** @Fields userType : 用户类型 */
	@Column(name = "user_type")
	private Integer userType;
	
	/** @Fields supplierId : 所属供应商Id */
	@Column(name = "supplier_id")
	private Long supplierId;
	
	/** @Fields username : 用户名 */
	@Column(name = "username")
	private String username;

	/** @Fields password : 密码 */
	@Column(name = "password")
	private String password;

	/** @Fields identityAccount : 身份账号 */
	@Column(name = "identity_account")
	private String identityAccount;
	
	/**
	 * 盐
	 */
	@Column(name = "salt")
	private String salt;

	/** @Fields email :邮箱 */
	@Column(name = "email")
	private String email;

	/** @Fields mobile : 手机号 */
	@Column(name = "mobile")
	private String mobile;

	/** @Fields status : 状态 0：禁用 1：正常 */
	@Column(name = "status")
	private Integer status;
	/**
	 * 部门ID
	 */
	@Column(name = "dept_id")
	private Long deptId;
	/**
	 * 部门编码
	 */
	@Column(name = "orgCode")
	private String orgCode;
	
	/** @Fields createUserId : 创建者ID */
	@Column(name = "create_user_id")
	private Long createUserId;

	/** @Fields createTime :创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "create_time")
	private Date createTime;

	/** @Fields position : 职位 */
	@Column(name = "position")
	private String position;
	
	/** @Fields position : 职位 */
	@Transient
	private String positionName;
	
	/** @Fields company : 所属公司 */
	@Column(name = "company_id")
	private Long company;

	/**
	 * 角色ID列表
	 * Transient注解就是在给某个javabean上需要添加个属性，但是这个属性你又不希望给存到数据库中去
	 * ，仅仅是做个临时变量，用一下。不修改已经存在数据库的数据的数据结构。
	 */
	@Transient
	private List<Long> roleIdList;
	
	/**
	 * 部门名称
	 */
	@Transient
	private SysDept sysDept;
	
	/**
	 * 部门名称
	 */
	@Transient
	private String deptName;
	
	/**
	 * 公司名称
	 */
	@Transient
	private String companyName;
	
	/**
	 * 所属供应商名称
	 */
	@Transient
	private String supplierName;
	
	/**
	 * 上级部门名称
	 */
	@Transient
	private String parentDeptName;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentityAccount() {
		return identityAccount;
	}

	public void setIdentityAccount(String identityAccount) {
		this.identityAccount = identityAccount;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getParentDeptName() {
		return parentDeptName;
	}

	public void setParentDeptName(String parentDeptName) {
		this.parentDeptName = parentDeptName;
	}
	
	

}
