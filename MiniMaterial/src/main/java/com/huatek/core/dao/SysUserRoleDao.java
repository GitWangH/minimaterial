/**
 * 
 */
package com.huatek.core.dao;

import java.util.List;

import com.huatek.core.entity.SysUserRole;

/**
  * @ClassName: SysUserRoleDao
  * @FullClassPath: com.frame.core.dao.SysUserRoleDao
  * @Description: 用户角色关联数据层
  * @author: Arno
  * @date: Sep 12, 2017 3:19:57 PM
  * @version: 1.0
  */

public interface SysUserRoleDao {
	
	/** 
	* @Title: deleteRoleByUserID 
	* @Description: 根据用户ID删除关联的角色 
	* @createDate: Sep 12, 2017 3:21:34 PM
	* @param userID
	* @return Integer
	*/ 
	public Integer deleteRoleByUserID(Long userID);
	
	/** 
	* @Title: queryRoleByUserID 
	* @Description: 根据用户ID获取用户拥有的角色 
	* @createDate: Sep 12, 2017 4:08:25 PM
	* @param userId
	* @return List<SysUserRole>
	*/ 
	public List<SysUserRole> queryRoleByUserID(Long userId);

	/** 
	* @Title: queryRoleIdByUserID 
	* @Description: TODO 
	* @createDate: Mar 20, 2018 4:55:42 PM
	* @param userId
	* @return 
	*/ 
	public SysUserRole queryRoleIdByUserID(Long userId);

}
