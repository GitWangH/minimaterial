package com.frame.business.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.frame.business.entity.${bean.className};
import com.frame.business.service.${bean.className}Service;
import com.frame.core.annotation.SysLog;

import com.frame.core.enums.QuerySqlTypeEnum;
import com.frame.core.security.SessionThreadLocal;
import com.frame.core.vo.PageData;
import com.frame.core.vo.QueryPage;
import com.frame.core.vo.QueryParam;
import com.frame.core.vo.R;


/**
  * @ClassName: ${bean.className}Controller
  * @Description: TODO.
  * @author: ${annotation.authorName}
  * @Email : ${annotation.authorMail}
  * @date: ${annotation.date}
  * @version: ${annotation.version}
  */
  
@RestController
@RequestMapping("${url}")
public class  ${bean.className}Controller {

	@Autowired
	private ${bean.className}Service ${tempService};
	
	
	/** 
	* @Title: list 
	* @Description:  列表 
	* @createDate: ${annotation.date}
	* @param Map<String, Object> map
	* @return R
	*/ 
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> map){
	    QueryPage queryPage = new QueryPage(map);
	    List<QueryParam> params = queryPage.getParams();
	    //TODO 查询条件
	   /* params.add(new QueryParam("materielType",QuerySqlTypeEnum.LIKE.getType(),map.get("materielType"),"String"));
	    params.add(new QueryParam("materielName",QuerySqlTypeEnum.LIKE.getType(),map.get("materielName"),"String"));
	    params.add(new QueryParam("status",QuerySqlTypeEnum.LIKE.getType(),map.get("status"),"String"));*/
	    
	    return R.success().put("page", findPage);
	}

	/** 
	* @Title: info 
	* @Description: 信息
	* @createDate: ${annotation.date}
	* @param id
	* @return R
	*/ 
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		${bean.className} find = ${tempService}.findByPk(id);
		return R.success().put("model", find);
	}
	
	/** 
	* @Title: save 
	* @Description:保存  
	* @createDate: ${annotation.date}
	* @param data
	* @return R
	*/ 
	@SysLog("保存")
	@RequestMapping("/save")
	public R save(@RequestBody ${bean.className} entity){
		${tempService}.save(entity);
		return R.success("保存成功");
	}
	
	/** 
	* @Title: delete 
	* @Description:  删除
	* @createDate: ${annotation.date}
	* @param userIds
	* @return 
	*/ 
	@SysLog("删除")
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] ids){
		List<Long> asList = Arrays.asList(ids);
		${tempService}.deleteByPK(asList);
		return R.success("删除成功");
	}
	
	
	/** 
	* @Title: update 
	* @Description:  修改
	* @createDate: ${annotation.date}
	* @param data
	* @return R
	*/ 
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody ${bean.className} entity){
		${tempService}.update(entity);
		return R.success("保存成功");
	}
    
}