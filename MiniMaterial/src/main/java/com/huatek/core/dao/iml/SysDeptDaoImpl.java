/**
 * 
 */
package com.huatek.core.dao.iml;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.huatek.core.dao.SysDeptDao;
import com.huatek.core.entity.SysDept;

/**
  * @ClassName: SysDeptServiceImpl
  * @FullClassPath: com.frame.core.dao.impl.SysDeptServiceImpl
  * @Description: 部门服务
  * @author: Arno
  * @date: Sep 14, 2017 12:11:57 PM
  * @version: 1.0
  */

@Repository
public class SysDeptDaoImpl extends BaseDaoImpl<SysDept, Long> implements SysDeptDao {

	/* (non-Javadoc)
	 * @see com.frame.core.dao.SysDeptDao#queryList()
	 */
	@Override
	public List<SysDept> queryList(List<Long> deptIds) {
		String sql = "";
		Map<String, Object> paramMap = getParamsMap();
		if(deptIds.isEmpty()){
			 sql = "select d.*,(select p.name from sys_dept p where p.dept_id = d.parent_id) as parentName from sys_dept d where 1=1 order by d.order_num asc";
		}else{
			 sql = "select d.*,(select p.name from sys_dept p where p.dept_id = d.parent_id) as parentName from sys_dept d  where 1=1 and d.dept_id in (:deptids) order by d.order_num asc";
		}
		if(!deptIds.isEmpty()){
			paramMap.put("deptids", deptIds);
		}
		List<Map<String, Object>> listMap = super.getListMap(sql, paramMap);
		List<SysDept> list = new ArrayList<>();
		if(null != listMap && !listMap.isEmpty()){
			buildSysDept(listMap, list);
		}
		return list;
	}

	/** 
	* @Title: buildSysDept 
	* @Description:  
	* @createDate: Sep 14, 2017 12:48:59 PM
	* @param listMap
	* @param list 
	*/ 
	private void buildSysDept(List<Map<String, Object>> listMap, List<SysDept> list) {
		for (Map<String,Object> map : listMap) {
			SysDept dept = new SysDept();
			dept.setDeptId(Long.valueOf(map.get("dept_id").toString()));
			dept.setParentId(map.get("parent_id") == null ? 0L : Long.valueOf(map.get("parent_id").toString()));
			dept.setName(map.get("name").toString());
			dept.setParentName(map.get("parentName") == null ? "" : map.get("parentName").toString());
			dept.setOrderNum(map.get("order_num") == null ? 0 : Integer.valueOf(map.get("order_num").toString()));
			dept.setTypeName(map.get("type").toString().equalsIgnoreCase("company") ? "公司" : "部门");
			dept.setOrgCode(map.get("orgcode").toString());
			list.add(dept);
		}
	}

	/* (non-Javadoc)
	 * @see com.frame.core.dao.SysDeptDao#queryDetpIdList(long)
	 */
	@Override
	public List<SysDept> queryDetpIdList(long deptId) {
		String hql = "from SysDept s where s.parentId=:parentId" ;
		Map<String, Object> paramsMap = getParamsMap();
		paramsMap.put("parentId", deptId);
		return super.getListByHQL(hql, paramsMap);
	}

	/* (non-Javadoc)
	 * @see com.frame.core.dao.SysDeptDao#getSubDeptIdList(java.lang.Long)
	 */
	@Override
	public String getSubDeptIdList(Long deptId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.frame.core.dao.SysDeptDao#getCompanyId(java.lang.Long)
	 */
	@Override
	public SysDept getCompanyId(Long parentId) {
		String hql = "from SysDept t where t.deptId =:parentId";
		Map<String, Object> map = getParamsMap();
		map.put("parentId", parentId);
		return getUniqueByHQL(hql, map);
	}

	/* (non-Javadoc)
	 * @see com.frame.core.dao.SysDeptDao#findByParebtOrgCode(java.lang.String)
	 */
	@Override
	public String findByParentOrgCode(String parentOrgCode) {
		int length = parentOrgCode.length()+3;
		String sql = "select Max(orgcode) as orgCode from sys_dept s where LENGTH(orgcode)=:lengths and s.orgcode like (:parentOrgCode) order by orgcode desc";
		Map<String, Object> map = getParamsMap();
		map.put("parentOrgCode", parentOrgCode+"%");
		map.put("lengths", length);
		List<Map<String, Object>> listMap = getListMap(sql, map);
		if(CollectionUtils.isNotEmpty(listMap)){
			Object object = listMap.get(0).get("orgCode");
			return object == null? "" : object.toString();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.frame.core.dao.SysDeptDao#getAll()
	 */
	@Override
	public List<Map<String,Object>> getAll() {
		StringBuilder sb = new StringBuilder("SELECT t1.dept_id as deptId, 	t1.parent_id as parentId, 	t1.`name` as deptName, t2.`name` as parentName, CONCAT(t2.`name`,t1.`name`) as fullName ")  ;
		sb.append(" from sys_dept t1 LEFT JOIN sys_dept t2 on t1.parent_id = t2.dept_id " );
		return super.getListMap(sb.toString(), null);
	}
	
	
	
	
}
