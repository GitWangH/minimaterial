/**
 * 
 */
package com.huatek.core.vo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.huatek.core.enums.QuerySqlTypeEnum;

/**
 * @ClassName: QueryParam
 * @FullClassPath: com.lpp.mq.core.QueryParam
 * @Description: 查询参数
 * @author: Arno
 * @date: 2017年3月7日 下午4:36:18
 * @version: 1.0
 */

public class QueryParam implements java.io.Serializable{

	/** @Fields serialVersionUID : */ 
	private static final long serialVersionUID = -3895043805465836624L;

	/** @Fields field : 查询字段*/ 
	private String field;

	/** @Fields logic : 操作符号.*/ 
	private String logic = QuerySqlTypeEnum.EQUAL.getType();

	/** @Fields value :查询值 */ 
	private Object value;
	
	/** @Fields type : 字段类型*/ 
	private String type = "String" ;
	

	public QueryParam() {
	}

	public QueryParam(String field, String logic, Object value,String type) {
		super();
		this.field = field;
		this.logic = logic;
		this.value = value;
		this.type = type;
	}
	
	public QueryParam(String field, String logic, Object value) {
		super();
		this.field = field;
		this.logic = logic;
		this.value = value;
	}
	
	public QueryParam(String field, Object value) {
		super();
		this.field = field;
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}


	public Object queryValue (){
		Object queryValue = null ;
		if(null != this.value ){
			try {
				
				if("".equals(this.value.toString())) return this.value;
				
				if(this.type.equalsIgnoreCase("int") 
						|| type.equalsIgnoreCase("integer") ){
					queryValue = Integer.valueOf(this.value.toString());
				}else if(this.type.equalsIgnoreCase("float") 
						|| this.type.equalsIgnoreCase("double")
						||  this.type.equalsIgnoreCase("BigDecimal")){
					queryValue = new BigDecimal(this.value.toString());
				}else if(this.type.equalsIgnoreCase("long") ){
					queryValue = Long.valueOf(this.value.toString());
				} else if(this.type.equalsIgnoreCase("date")){
					queryValue = new SimpleDateFormat("yyyy-MM-dd").parse(this.value.toString());
				}else if(this.type.equalsIgnoreCase("dateTime")){
					queryValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.value.toString());
				}else {
					queryValue =this.value;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return queryValue ;
	}
}
