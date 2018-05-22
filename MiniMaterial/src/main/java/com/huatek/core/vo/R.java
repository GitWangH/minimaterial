package com.huatek.core.vo;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName: R
 * @FullClassPath: com.frame.core.vo.R
 * @Description: 返回数据
 * @author: Arno
 * @date: Sep 11, 2017 2:21:12 PM
 * @version: 1.0
 */

public class R extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public R() {
		
	}

	public static R error(String msg) {
		R r = new R();
		r.put("success", false);
		r.put("msg", msg);
		return r;
	}

	public static R success(String msg) {
		R r = new R();
		r.put("success", true);
		r.put("msg", msg);
		return r;
	}

	public static R success(Map<String, Object> map) {
		R r = new R();
		r.put("success", true);
		r.putAll(map);
		return r;
	}

	public static R success() {
		R r = new R();
		r.put("success", true);
		return r;
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
