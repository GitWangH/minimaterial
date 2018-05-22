/**
 * 
 */
package com.huatek.core.dao.iml;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.huatek.core.dao.SysUserRoleDao;
import com.huatek.core.entity.SysUserRole;

/**
  * @ClassName: SysUserRoleDaoImpl
  * @FullClassPath: com.frame.core.dao.impl.SysUserRoleDaoImpl
  * @Description: 用户角色关联数据层
  * @author: Arno
  * @date: Sep 12, 2017 3:20:25 PM
  * @version: 1.0
  */
@Repository
public class SysUserRoleDaoImpl extends BaseDaoImpl<SysUserRole, Long> implements SysUserRoleDao {

	/* (non-Javadoc)
	 * @see com.frame.core.dao.SysUserRoleDao#deleteRoleByUserID(java.lang.Long)
	 */
	@Override
	public Integer deleteRoleByUserID(Long userID) {
		String hql = "delete from SysUserRole where userId=:userid";
		Map<String, Object> paramMap = getParamsMap();
		paramMap.put("userid", userID);
		return super.updateHql(hql, paramMap);
	}

	/* (non-Javadoc)
	 * @see com.frame.core.dao.SysUserRoleDao#queryRoleIdList(java.lang.Long)
	 */
	@Override
	public List<SysUserRole> queryRoleByUserID(Long userId) {
		String hql = "from SysUserRole s where s.userId =:userId" ;
		Map<String, Object> paramsMap = getParamsMap();
		paramsMap.put("userId", userId);
		return getListByHQL(hql, paramsMap);
	}

	/* (non-Javadoc)
	 * @see com.frame.core.dao.SysUserRoleDao#queryRoleIdByUserID(java.lang.Long)
	 */
	@Override
	public SysUserRole queryRoleIdByUserID(Long userId) {
		String hql = "from SysUserRole s where s.userId =:userId" ;
		Map<String, Object> paramsMap = getParamsMap();
		paramsMap.put("userId", userId);
		return getUniqueByHQL(hql, paramsMap);
	}
	
}
