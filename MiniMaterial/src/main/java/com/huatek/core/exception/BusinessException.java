/**
 * 
 */
package com.huatek.core.exception;

/**
 * @ClassName: BusinessException
 * @FullClassPath: com.lpp.mq.exception.BusinessException
 * @Description: 业务异常
 * @author: Arno
 * @date: 2017年3月9日 下午5:50:43
 * @version: 1.0
 */

public class BusinessException extends RuntimeException  {

	private static final long serialVersionUID = 1L;

	/** @Fields bizType : 业务类型 */
	private String bizType;

	/** @Fields bizCode :业务代码 */
	private int bizCode;

	/** @Fields message : 错误信息 */
	private String message;

	public BusinessException(String bizType, int bizCode, String message) {
		super(message);
		this.bizType = bizType;
		this.bizCode = bizCode;
		this.message = message;
	}

	public BusinessException(String msg, Throwable e) {
		super(msg, e);
		this.message = msg;
	}
	
	public BusinessException(String message) {
		super(message);
		this.bizType = "";
		this.bizCode = -1;
		this.message = message;
	}

	public BusinessException(String bizType, String message) {
		super(message);
		this.bizType = bizType;
		this.bizCode = -1;
		this.message = message;
	}

	public BusinessException(int bizCode, String message) {
		super(message);
		this.bizType = "";
		this.bizCode = bizCode;
		this.message = message;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public int getBizCode() {
		return bizCode;
	}

	public void setBizCode(int bizCode) {
		this.bizCode = bizCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
