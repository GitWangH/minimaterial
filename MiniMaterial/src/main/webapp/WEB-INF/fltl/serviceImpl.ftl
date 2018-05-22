package com.frame.business.service.impl;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.core.service.BaseService;
import com.frame.core.dao.BaseDao;
import com.frame.business.dao.${bean.className}Dao;
import com.frame.business.entity.${bean.className};
import com.frame.business.service.${bean.className}Service;
import com.frame.core.service.impl.BaseServiceImpl;



/**
  * @ClassName: ${bean.className}ServiceImpl
  * @Description: TODO.
  * @author: ${annotation.authorName}
  * @Email : ${annotation.authorMail}
  * @date: ${annotation.date}
  * @version: ${annotation.version}
  */
@Service
@Transactional
public class ${bean.className}ServiceImpl extends BaseServiceImpl< ${bean.className}, Long> implements ${bean.className}Service {
	
	@Autowired
	private BaseDao< ${bean.className}, Long> baseDao;
	
	
	@Autowired
	private ${bean.className}Dao ${temp};
	
	
	/* (non-Javadoc)
	 * @see com.frame.core.service.BaseService#getBaseDao()
	 */
	@Override
	public BaseDao<BusiPurchaseApply, Long> getBaseDao() {
		return baseDao;
	}
}
