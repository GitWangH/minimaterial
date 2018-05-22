/**
 * 
 */
package com.huatek.core.service;

import java.util.List;

import com.huatek.core.entity.SysUserRole;

/**
  * @ClassName: SysUserRoleService
  * @FullClassPath: com.frame.core.service.SysUserRoleService
  * @Description: 用户角色关联服务
  * @author: Arno
  * @date: Sep 12, 2017 3:15:42 PM
  * @version: 1.0
  */

public interface SysUserRoleService extends BaseService<SysUserRole, Long> {

	
	/** 
	* @Title: saveOrUpdate 
	* @Description: 保存或者更新用户关联的角色 
	* @createDate: Sep 12, 2017 3:18:07 PM
	* @param userId
	* @param roleIdList 
	*/ 
	public void saveOrUpdate(Long userId, List<Long> roleIdList) ;
	
	
	/** 
	* @Title: queryRoleIdList 
	* @Description: 根据用户ID获取用户拥有的角色 
	* @createDate: Sep 12, 2017 4:08:25 PM
	* @param userId
	* @return List<Long>
	*/ 
	public List<Long> queryRoleIdList(Long userId);


	/** 
	* @Title: deleteRelationByRoles 
	* @Description: 删除角色与用户关系 
	* @createDate: Sep 12, 2017 7:35:32 PM
	* @param roles 
	*/ 
	public void deleteRelationByRoles(List<Long> roles);


	/** 
	* @Title: queryRoleIdByUserID 
	* @Description: TODO 
	* @createDate: Mar 20, 2018 4:55:05 PM
	* @param userId
	* @return 
	*/ 
	public SysUserRole queryRoleIdByUserID(Long userId);
	
}
