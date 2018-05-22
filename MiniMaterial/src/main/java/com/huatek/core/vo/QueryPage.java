/**
 * 
 */
package com.huatek.core.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
  * @ClassName: QueryPage
  * @FullClassPath: com.lpp.mq.core.QueryPage
  * @Description: 查询条件
  * @author: Arno
  * @date: 2017年3月7日 下午4:35:55
  * @version: 1.0
  */

public class QueryPage implements java.io.Serializable {
	
	/** @Fields serialVersionUID : */ 
	private static final long serialVersionUID = -5830288467941853735L;
	
	/** @Fields page :当前页 */ 
	private int page = 1;
	
	/** @Fields pageSize : 每页行*/ 
	private int pageSize = 10;
	
	/** @Fields orderByField : 排序字段*/ 
	private String orderByField;
	
	/** @Fields oderbyType :排序类型 */ 
	private String oderbyType ="asc" ;

	/** @Fields params : 参数*/ 
	private List<QueryParam> params = new ArrayList<>();

	/**
	 * 
	 */
	public QueryPage() {
		// TODO Auto-generated constructor stub
	}
	
	public QueryPage(Map<String, Object> params) {
		if(null != params){
			this.page = Integer.parseInt(params.get("page").toString());
			this.pageSize = Integer.parseInt(params.get("limit").toString());
			this.oderbyType = params.get("order") == null ? "" : params.get("order").toString();
			this.orderByField = params.get("sidx")== null ? "" : params.get("sidx").toString();
		}
	}
	
	
	public QueryPage(Map<String, Object> params, String type) {
		if(null != params){
			this.page = Integer.parseInt(params.get("offset").toString());
			this.pageSize = Integer.parseInt(params.get("limit").toString());
			this.oderbyType = params.get("sortOrder") == null ? "" : params.get("sortOrder").toString();
			this.orderByField = params.get("sortName")== null ? "" : params.get("sortName").toString();
		}
	}
	
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page == 0){
			this.page = 1;
		}else{
			this.page = page+1;
		}
		
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<QueryParam> getParams() {
		return params;
	}

	public void setParams(List<QueryParam> params) {
		this.params = params;
	}

	public String getOrderByField() {
		return orderByField;
	}

	public void setOrderByField(String orderByField) {
		this.orderByField = orderByField;
	}

	public String getOderbyType() {
		return oderbyType;
	}

	public void setOderbyType(String oderbyType) {
		this.oderbyType = oderbyType;
	}
	
	public void addParams(QueryParam params){
		getParams().add(params);
	}
}
