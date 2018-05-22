/**
 * 
 */
package com.huatek.core.dao;

import java.util.List;
import java.util.Map;

import com.huatek.core.entity.SysDept;

/**
  * @ClassName: SysDeptService
  * @FullClassPath: com.frame.core.dao.SysDeptService
  * @Description: 部门服务
  * @author: Arno
  * @date: Sep 14, 2017 12:11:13 PM
  * @version: 1.0
  */

public interface SysDeptDao {

	/** 
	* @Title: queryList 
	* @Description: 获取部门集合 
	* @createDate: Sep 14, 2017 12:43:53 PM
	* @return List<SysDept>
	*/ 
	List<SysDept> queryList(List<Long> deptIds);

	/** 
	* @Title: queryDetpIdList 
	* @Description: TODO 
	* @createDate: Sep 14, 2017 2:07:01 PM
	* @param deptId
	* @return 
	*/ 
	List<SysDept> queryDetpIdList(long deptId);

	/** 
	* @Title: getSubDeptIdList 
	* @Description: TODO 
	* @createDate: Sep 14, 2017 4:08:43 PM
	* @param deptId
	* @return 
	*/ 
	String getSubDeptIdList(Long deptId);

	/** 
	* @Title: getCompanyId 
	* @Description: TODO 
	* @createDate: Oct 16, 2017 4:22:52 PM
	* @param parentId
	* @return 
	*/ 
	SysDept getCompanyId(Long parentId);

	/** 
	* @Title: findByParentOrgCode 
	* @Description: TODO 
	* @createDate: Nov 10, 2017 6:31:13 PM
	* @param parentOrgCode 
	*/ 
	String findByParentOrgCode(String parentOrgCode);
	
	
	/** 
	* @Title: getAll 
	* @Description: 获取全部 
	* @createDate: Apr 2, 2018 10:44:48 AM
	* @return 
	*/ 
	List<Map<String,Object>> getAll();

}
