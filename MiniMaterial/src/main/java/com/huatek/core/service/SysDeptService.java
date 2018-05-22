package com.huatek.core.service;

import java.util.List;
import java.util.Map;

import com.huatek.core.entity.SysDept;

public interface SysDeptService extends BaseService<SysDept,Long> {


	/**
	 * @Title: queryList
	 * @Description: 获取部门集合
	 * @createDate: Sep 14, 2017 12:43:20 PM
	 * @return
	 */
	List<SysDept> queryList(List<Long> deptIds);

	/**
	 * @Title: queryDetpIdList
	 * @Description: 根据父id获取子部门
	 * @createDate: Sep 14, 2017 2:06:24 PM
	 * @param deptId
	 * @return
	 */
	List<SysDept> queryDetpIdList(long deptId);

	/**
	 * @Title: getSubDeptIdList
	 * @Description: 获取子部门ID
	 * @createDate: Sep 14, 2017 4:07:25 PM
	 * @param deptId
	 * @return
	 */
	List<Long> getSubDeptIdList(Long deptId);

	Long getCompanyId(Long parentId);

	/**
	 * @Title: getAll
	 * @Description: 获取全部
	 * @createDate: Apr 2, 2018 10:44:48 AM
	 * @return
	 */
	List<Map<String, Object>> getAll();
}
