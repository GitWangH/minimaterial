package com.huatek.core.dao.iml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.huatek.core.dao.SysUserDao;
import com.huatek.core.entity.SysUser;

@Repository
public class SysUserDaoimpl extends BaseDaoImpl<SysUser, Long> implements SysUserDao {

	@Override
	public SysUser findUserByNameAndPwd(String userName, String pwd) {
		String hql = "from SysUser t where t.username =:username and t.password=:password" ;
		Map<String, Object> map = getParamsMap();
		map.put("username", userName);
		map.put("password", pwd);
		return getUniqueByHQL(hql, map);
	}

	/* (non-Javadoc)
	 * @see com.frame.core.dao.SysUserDao#queryAllMenuId(java.lang.Long)
	 */
	@Override
	public List<Long> queryAllMenuId(Long userId) {
		String sql = "SELECT DISTINCT 	rm.menu_id  FROM 	sys_user_role ur LEFT JOIN sys_role_menu rm ON ur.role_id = rm.role_id WHERE ur.user_id=:userid";
		Map<String, Object> map = getParamsMap();
		map.put("userid", userId);
		List<?> listBySQL = getListBySQL2(sql, map);
		List<Long> temp = new ArrayList<>();
		for (Object object : listBySQL) {
			temp.add(Long.valueOf(object.toString()));
		}
		return temp;
	}

	/* (non-Javadoc)
	 * @see com.frame.core.dao.SysUserDao#getUserPermissions(java.lang.Long)
	 */
	@Override
	public List<?> getUserPermissions(Long userId) {
		String sql = "select m.perms from sys_user_role ur LEFT JOIN sys_role_menu rm on ur.role_id = rm.role_id LEFT JOIN sys_menu m on rm.menu_id = m.menu_id where ur.user_id = :userid";
		Map<String, Object> map = getParamsMap();
		map.put("userid", userId);
		return  getListBySQL2(sql, map);
	}

	/* (non-Javadoc)
	 * @see com.frame.core.dao.SysUserDao#getPurchaser()
	 */
	@Override
	public List<SysUser> getProvicesManager() {
		String hql = "from SysUser t where t.position ='PROVICES_MANAGER'" ;
		Map<String, Object> paramsMap = getParamsMap();
		return super.getListByHQL(hql, paramsMap);
	}

	/* (non-Javadoc)
	 * @see com.frame.core.dao.SysUserDao#findApprover(int)
	 */
	@Override
	public List<SysUser> findApprover() {
		String hql = "from SysUser t where t.userType ='0' and t.status = '1' and t.position=:position" ;
		Map<String, Object> paramsMap = getParamsMap();
		paramsMap.put("position", "BUSINESS_HEAD");
		return super.getListByHQL(hql, paramsMap);
	}

	/* (non-Javadoc)
	 * @see com.frame.core.dao.SysUserDao#findUserByName(java.lang.String)
	 */
	@Override
	public SysUser findUserByName(String username) {
		String hql = "from SysUser t where t.username =:username" ;
		Map<String, Object> map = getParamsMap();
		map.put("username", username);
		return getUniqueByHQL(hql, map);
	}

}
