package com.huatek.core.enums;

public enum QuerySqlTypeEnum {
	IN("IN"),
	AND("AND"),
	EQUAL("="),
	NOTEQUAL("<>"),
	ALT("<"),
	GLT(">"),
	
	ALT_EQUAL("<="),
	GLT_EQUAL(">="),
	
	ASC("ASC"),
	LIKE("LIKE"),
	DESC("DESC"),
	LEFT_LIKE("LIKE"),
	RIGHT_LIKE("LIKE"),
	ORDER_BY("ORDER BY");
	
	private String type ;

	/**
	 * 
	 */
	private QuerySqlTypeEnum() {

	}
	
	private QuerySqlTypeEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
