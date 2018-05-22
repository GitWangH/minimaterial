package com.huatek.core.service.iml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;




import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatek.core.dao.SysDeptDao;
import com.huatek.core.dao.BaseDao;
import com.huatek.core.entity.SysDept;
import com.huatek.core.service.SysDeptService;


@Service
@Transactional
public class SysDeptServiceImpl extends BaseServiceImpl<SysDept, Long> implements SysDeptService {

	
	@Autowired
	private BaseDao<SysDept, Long> baseDao;
	
	
	@Autowired
	private SysDeptDao sysDeptDao;
	
	@Override
	public BaseDao<SysDept, Long> getBaseDao() {
		
		return baseDao;
	}

	/* (non-Javadoc)
	 * @see com.frame.core.service.impl.BaseServiceImpl#save(java.lang.Object)
	 */
	@Override
	public Serializable save(SysDept t) {
		String parentOrgCode = t.getParentOrgCode();
		String orgCode = sysDeptDao.findByParentOrgCode(parentOrgCode);
		if(StringUtils.isNoneBlank(orgCode)){
			String substring = orgCode.substring(parentOrgCode.length(), orgCode.length());
			String createOrgCode = createOrgCode(parentOrgCode, substring);
			t.setOrgCode(createOrgCode);
		}else{
			t.setOrgCode(parentOrgCode+"001");
		}
		return super.save(t);
	}
	
	/* (non-Javadoc)
	 * @see com.frame.core.service.impl.BaseServiceImpl#update(java.lang.Object)
	 */
	@Override
	public void update(SysDept t) {
		Long parentId = t.getParentId();
		SysDept parent = super.findByPk(parentId);
		String parentOrgCode =parent.getOrgCode();
		String orgCode = sysDeptDao.findByParentOrgCode(parentOrgCode);
		if(StringUtils.isNoneBlank(orgCode)){
			if(!orgCode.equals(t.getOrgCode())){
				t.setOrgCode(orgCode);
			}
			/*String substring = orgCode.substring(parentOrgCode.length(), orgCode.length());
			String createOrgCode = createOrgCode(parentOrgCode, substring);
			t.setOrgCode(createOrgCode);*/
		}else{
			t.setOrgCode(parentOrgCode+"001");
		}
		super.update(t);
	}

	/* (non-Javadoc)
	 * @see com.frame.core.service.SysDeptService#queryList()
	 */
	@Override
	public List<SysDept> queryList(List<Long> deptIds) {
		return sysDeptDao.queryList(deptIds);
	}

	/* (non-Javadoc)
	 * @see com.frame.core.service.SysDeptService#queryDetpIdList(long)
	 */
	@Override
	public List<SysDept> queryDetpIdList(long deptId) {
		return sysDeptDao.queryDetpIdList(deptId);
	}
	
	/* (non-Javadoc)
	 * @see com.frame.core.service.SysDeptService#getSubDeptIdList(java.lang.Long)
	 */
	@Override
	public List<Long>  getSubDeptIdList(Long deptId) {
		// 部门及子部门ID列表
		List<Long> deptIdList = new ArrayList<>();
		// 获取子部门ID
		List<SysDept> queryDetpIdList = queryDetpIdList(deptId);
		List<Long> subIdList = getSubDeptID(queryDetpIdList);
		getDeptTreeList(subIdList, deptIdList);
		// 添加本部门
		deptIdList.add(deptId);
		return deptIdList;
				
	}
	
	public List<Long> getSubDeptID(List<SysDept> listDept ){
		List<Long>  list = new ArrayList<>();
		for (SysDept dept : listDept) {
			list.add(dept.getDeptId());
		}
		return list ;
	}
	
	
	/**
	 * 递归
	 */
	public void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList){
		for(Long deptId : subIdList){
			List<SysDept> queryDetpIdList = queryDetpIdList(deptId);
			List<Long> list = getSubDeptID(queryDetpIdList);
			if(list.size() > 0){
				getDeptTreeList(list, deptIdList);
			}
			deptIdList.add(deptId);
		}
	}


	/* (non-Javadoc)
	 * @see com.frame.core.service.SysDeptService#getCompanyId(java.lang.Long)
	 */
	@Override
	public Long getCompanyId(Long deptId) {
		if(null == deptId){
			return null ;
		}
		SysDept dept = baseDao.findByPk(deptId);
		String type = dept.getType();
		if(type.equalsIgnoreCase("company")){
			return deptId ;
		}else{
			Long parentId = dept.getParentId();
			return getCompanyId(parentId);
		}
	}
	
	/** 
	* @Title: createOrgCode 
	* @Description: 生产机构编码 
	* @createDate: Nov 10, 2017 7:14:52 PM
	* @param parentOrgCode
	* @param orgCode
	* @return String
	*/ 
	public  String createOrgCode(String parentOrgCode ,String  orgCode) {
		int i = Integer.valueOf(orgCode)+1;
		String temp = i+"";
		if(temp.length()==2){
			temp="0"+temp;
		}else{
			temp="00"+temp;
		}
		return parentOrgCode+temp;
	}
	
	/* (non-Javadoc)
	 * @see com.frame.core.service.SysDeptService#getAll()
	 */
	@Override
	public List<Map<String, Object>> getAll() {
		return sysDeptDao.getAll();
	}
}
