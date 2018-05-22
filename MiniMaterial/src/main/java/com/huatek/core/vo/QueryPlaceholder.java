/**
 * 
 */
package com.huatek.core.vo;

/**
 * @ClassName: QueryPlaceholder
 * @FullClassPath: com.lpp.mq.core.QueryPlaceholder
 * @Description: 查询占位符
 * @author: Arno
 * @date: 2017年3月7日 下午5:34:30
 * @version: 1.0
 */

public class QueryPlaceholder {

	private String placeholder;

	private String type;

	private Object value;
	
	private String operator;

	public QueryPlaceholder() {
	}

	public QueryPlaceholder(String placeholder, String type, Object value) {
		super();
		this.placeholder = placeholder;
		this.type = type;
		this.value = value;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
