/**
 * 
 */
package com.huatek.core.service.iml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatek.core.dao.BaseDao;
import com.huatek.core.dao.SysUserRoleDao;
import com.huatek.core.entity.SysUserRole;
import com.huatek.core.service.SysUserRoleService;

/**
  * @ClassName: SysUserRoleServiceImpl
  * @FullClassPath: com.frame.core.service.impl.SysUserRoleServiceImpl
  * @Description: 用户角色关联服务
  * @author: Arno
  * @date: Sep 12, 2017 3:16:41 PM
  * @version: 1.0
  */

@Service
@Transactional
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, Long> implements SysUserRoleService {

	@Autowired
	private BaseDao<SysUserRole, Long> baseDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	/* (non-Javadoc)
	 * @see com.frame.core.service.BaseService#getBaseDao()
	 */
	@Override
	public BaseDao<SysUserRole, Long> getBaseDao() {
		return baseDao;
	}

	/* (non-Javadoc)
	 * @see com.frame.core.service.SysUserRoleService#saveOrUpdate(java.lang.Long, java.util.List)
	 */
	@Override
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		if(roleIdList.size() == 0){
			return ;
		}
		//先删除用户与角色关系
		sysUserRoleDao.deleteRoleByUserID(userId);
		List<SysUserRole> entitylist = new ArrayList<>();
		for (Long roleID : roleIdList) {
			SysUserRole role = new SysUserRole(userId, roleID);
			entitylist.add(role);
		}
		baseDao.saveBatch(entitylist);
	}
	
	/* (non-Javadoc)
	 * @see com.frame.core.service.SysUserRoleService#queryRoleIdList(java.lang.Long)
	 */
	@Override
	public List<Long> queryRoleIdList(Long userId) {
		List<Long> roleList = new ArrayList<>();
		List<SysUserRole> userRoleList = sysUserRoleDao.queryRoleByUserID(userId);
		if(null != userRoleList && !userRoleList.isEmpty()){
			for (SysUserRole sysUserRole : userRoleList) {
				roleList.add(sysUserRole.getRoleId());
			}
		}
		return roleList ;
	}

	/* (non-Javadoc)
	 * @see com.frame.core.service.SysUserRoleService#deleteRelationByRoles(java.util.List)
	 */
	@Override
	public void deleteRelationByRoles(List<Long> roles) {
		String hql = "delete SysUserRole where roleId in (:roles)" ;
		Map<String, Object> paramsMap = getParamsMap();
		paramsMap.put("roles", roles);
		baseDao.updateHql(hql, paramsMap);
	}

	/* (non-Javadoc)
	 * @see com.frame.core.service.SysUserRoleService#queryRoleIdByUserID(java.lang.Long)
	 */
	@Override
	public SysUserRole queryRoleIdByUserID(Long userId) {
		return sysUserRoleDao.queryRoleIdByUserID(userId);
	}
}
