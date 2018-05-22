package com.huatek.core.dao;

import java.util.List;

import com.huatek.core.entity.SysUser;

public interface SysUserDao{
    
	SysUser findUserByNameAndPwd(String userName, String pwd);
	
	/** 
	* @Title: queryAllMenuId 
	* @Description:  查询用户的所有菜单ID 
	* @createDate: Sep 11, 2017 5:38:25 PM
	* @param userId
	* @return List<Long>
	*/ 
	List<Long> queryAllMenuId(Long userId);

	/** 
	* @Title: getUserPermissions 
	* @Description: 获取用户权限 
	* @createDate: Sep 11, 2017 8:32:00 PM
	* @param userId
	* @return List<?>
	*/ 
	List<?> getUserPermissions(Long userId);

	/** 
	* @Title: getPurchaser 
	* @Description: TODO 
	* @createDate: Oct 13, 2017 3:30:47 PM
	* @return 
	*/ 
	List<SysUser> getProvicesManager();

	/** 
	* @Title: findApprover 
	* @Description: TODO 
	* @createDate: Mar 15, 2018 6:12:04 PM
	* @param i
	* @return 
	*/ 
	List<SysUser> findApprover();

	/** 
	* @Title: findUserByName 
	* @Description: TODO 
	* @createDate: Mar 15, 2018 6:52:37 PM
	* @param username
	* @return 
	*/ 
	SysUser findUserByName(String username);
	
}
