/**
 * 
 */
package com.huatek.core.service;

import java.util.List;

import com.huatek.core.entity.SysUser;

/**
  * @ClassName: SysUserService
  * @FullClassPath: com.frame.core.service.SysUserService
  * @Description: 用户管理服务
  * @author: Arno
  * @date: Sep 11, 2017 3:25:38 PM
  * @version: 1.0
  */

public interface SysUserService extends BaseService<SysUser, Long>{

	public SysUser findUserByNameAndPwd(String userName, String pwd);

	/** 
	* @Title: queryAllMenuId 
	* @Description:  查询用户的所有菜单ID 
	* @createDate: Sep 11, 2017 5:36:29 PM
	* @param userId 
	* @return  List<Long>
	*/ 
	public List<Long> queryAllMenuId(Long userId);

	/** 
	* @Title: getUserPermissions 
	* @Description: 获取用户权限 
	* @createDate: Sep 11, 2017 8:30:50 PM
	* @param userId
	* @return List<?>
	*/ 
	public List<?> getUserPermissions(Long userId);

	/** 
	* @Title: updatePassword 
	* @Description: 修改密码 
	* @createDate: Sep 13, 2017 11:22:26 AM
	* @param userId
	* @param password
	* @param newPassword
	* @return 
	*/ 
	public int updatePassword(Long userId, String password, String newPassword);

	/** 
	* @Title: getProvicesManager 
	* @Description: TODO 
	* @createDate: Oct 13, 2017 3:28:23 PM
	* @return 
	*/ 
	public List<SysUser> getProvicesManager();

	/** 
	* @Title: findApprover 
	* @Description: TODO 
	* @createDate: Mar 15, 2018 6:11:19 PM
	* @param i
	* @return 
	*/ 
	public List<SysUser> findApprover();

	/** 
	* @Title: findUserByName 
	* @Description: TODO 
	* @createDate: Mar 15, 2018 6:51:26 PM
	* @param username
	* @return 
	*/ 
	public SysUser findUserByName(String username);

}
