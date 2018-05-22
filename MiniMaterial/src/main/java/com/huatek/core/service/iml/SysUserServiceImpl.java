/**
 * 
 */
package com.huatek.core.service.iml;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatek.core.dao.BaseDao;
import com.huatek.core.dao.SysUserDao;
import com.huatek.core.entity.SysDept;
import com.huatek.core.entity.SysUser;
import com.huatek.core.exception.BusinessException;
import com.huatek.core.service.SysDeptService;
import com.huatek.core.service.SysUserRoleService;
import com.huatek.core.service.SysUserService;

/**
  * @ClassName: SysUserServiceImpl
  * @FullClassPath: com.frame.core.service.impl.SysUserServiceImpl
  * @Description: 用户管理服务
  * @author: Arno
  * @date: Sep 11, 2017 3:26:37 PM
  * @version: 1.0
  */
@Transactional
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Long> implements SysUserService {

	/* (non-Javadoc)
	 * @see com.frame.core.service.impl.BaseServiceImpl#getBaseDao()
	 */
	@Autowired
	private  BaseDao< SysUser, Long> baseDao;
	
	@Autowired
	private  SysUserDao userDao;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@Autowired
	private SysDeptService sysDeptService;
	
	
	@Override
	public BaseDao<SysUser, Long> getBaseDao() {
		return baseDao;
	}
	
	/* (non-Javadoc)
	 * @see com.frame.core.service.SysUserService#findUserByNameAndPwd(java.lang.String, java.lang.String)
	 */
	@Override
	public SysUser findUserByNameAndPwd(String userName, String pwd) {
		return userDao.findUserByNameAndPwd(userName, pwd);
	}

	/* (non-Javadoc)
	 * @see com.frame.core.service.SysUserService#queryAllMenuId(java.lang.Long)
	 */
	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return userDao.queryAllMenuId(userId);
	}
	

	/* (non-Javadoc)
	 * @see com.frame.core.service.SysUserService#getUserPermissions(java.lang.Long)
	 */
	@Override
	public List<?> getUserPermissions(Long userId) {
		return userDao.getUserPermissions(userId);
	}
	
	
	/* (non-Javadoc)
	 * @see com.frame.core.service.impl.BaseServiceImpl#save(java.lang.Object)
	 */
	@Override
	public Serializable save(SysUser user) {
		String result = validateUser(user);
		if(StringUtils.isNotBlank(result)){
			throw new BusinessException(result);
		}
		user.setCreateTime(new Date());
		Serializable pk = baseDao.save(user);
		sysUserRoleService.saveOrUpdate((Long)pk, user.getRoleIdList());
		return pk;
	}

	
	
	@Override
	public void update(SysUser user) {
		SysUser find = this.findUserByName(user.getUsername());
		if(null != find){
			if(!find.getUserId().toString().equals(user.getUserId().toString())){
				throw new BusinessException("用户名已存在");
			}else{
				if(user.getUserType() == 1){
					find.setUserType(user.getUserType());
					find.setSupplierId(user.getSupplierId());
					find.setUsername(user.getUsername());
					find.setPassword(user.getPassword());
					find.setIdentityAccount(user.getIdentityAccount());
					find.setEmail(user.getEmail());
					find.setMobile(user.getMobile());
					find.setPosition(user.getPosition());
					find.setStatus(user.getStatus());
					baseDao.update(find);
				}else{
					Long companyId = sysDeptService.getCompanyId(user.getDeptId());
					SysDept findByPk = sysDeptService.findByPk(user.getDeptId());
					find.setUserType(user.getUserType());
					find.setSupplierId(user.getSupplierId());
					find.setUsername(user.getUsername());
					find.setPassword(user.getPassword());
					find.setIdentityAccount(user.getIdentityAccount());
					find.setCompany(companyId);
					find.setOrgCode(findByPk.getOrgCode());
					find.setEmail(user.getEmail());
					find.setMobile(user.getMobile());
					find.setDeptId(user.getDeptId());
					find.setPosition(user.getPosition());
					find.setStatus(user.getStatus());
					baseDao.update(find);
				}
			}
		}else{
			if(user.getUserType() == 0){
				Long companyId = sysDeptService.getCompanyId(user.getDeptId());
				SysDept findByPk = sysDeptService.findByPk(user.getDeptId());
				user.setCompany(companyId);
				user.setOrgCode(findByPk.getOrgCode());
				baseDao.update(user);
			}
		}
	;
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}
	
	/* (non-Javadoc)
	 * @see com.frame.core.service.SysUserService#updatePassword(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public int updatePassword(Long userId, String password, String newPassword) {
		SysUser user = baseDao.findByPk(userId);
		if(!user.getPassword().equals(password)){
			return 0 ;
		}
		user.setPassword(newPassword);
		baseDao.update(user);
		return 1;
	}
	
	/** 
	* @Title: validateUser 
	* @Description: 校验用户 
	* @createDate: Sep 12, 2017 3:00:05 PM
	* @param user 
	*/ 
	private String validateUser(SysUser user) {
		
		return null ;
	}

	/* (non-Javadoc)
	 * @see com.frame.core.service.SysUserService#getPurchaser()
	 */
	@Override
	public List<SysUser> getProvicesManager() {
		return userDao.getProvicesManager();
	}

	/* (non-Javadoc)
	 * @see com.frame.core.service.SysUserService#findApprover(int)
	 */
	@Override
	public List<SysUser> findApprover() {
		return userDao.findApprover();
	}

	/* (non-Javadoc)
	 * @see com.frame.core.service.SysUserService#findUserByName(java.lang.String)
	 */
	@Override
	public SysUser findUserByName(String username) {
		return userDao.findUserByName(username);
	}
	

}
