/**
 * 
 */
package com.huatek.core.security;

import java.io.Serializable;

import com.huatek.core.entity.SysUser;

/**
 * @ClassName: SessionThreadLocal
 * @FullClassPath: com.frame.core.dynamic.SessionThreadLocal
 * @Description: TODO
 * @author: Arno
 * @date: Sep 12, 2017 5:19:42 PM
 * @version: 1.0
 */

public class SessionThreadLocal implements Serializable {
	
	/** @Fields serialVersionUID : */ 
	private static final long serialVersionUID = -5951161660531231746L;
	
	
	private static ThreadLocal<SysUser> threadLocal = new ThreadLocal<SysUser>();

	/**
	 * 设置用户信息
	 * 
	 * @param user
	 */
	public static void setUser(SysUser user) {
		threadLocal.set(user);
	}

	/**
	 * 获取登录用户信息
	 * 
	 * @return
	 */
	public static SysUser getUser() {
		System.out.println("当前线程：" + Thread.currentThread().getName());
		return threadLocal.get();
	}
	
	public static void remove() {
		threadLocal.set(null);
	}
}
