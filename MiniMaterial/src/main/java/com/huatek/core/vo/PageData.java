package com.huatek.core.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PageData
 * @FullClassPath: com.lpp.mq.core.PageData
 * @Description: 分页封装类, 用于做分页查询的基础类，封装了一些分页的相关属性.
 * @author: Arno
 * @date: 2017年3月6日 上午11:10:06
 * @version: 1.0
 * @param <T>
 */

public class PageData<T> implements java.io.Serializable{

	/** @Fields serialVersionUID : */ 
	private static final long serialVersionUID = 6738678976285333152L;
	
	/** @Fields DEFAULT_PAGE_SIZE : 默认行数*/ 
	private static final int DEFAULT_PAGE_SIZE = 10;

	/** @Fields currentPage : 当前页 */
	private int currentPage;

	/** @Fields pageNo : 下一页 */
	private int pageNo;

	/** @Fields pageSize : 每页行数 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/** @Fields totalCount : 总条数 */
	private int totalCount;

	/** @Fields pageCount :总页数 */
	private int pageCount;

	/** @Fields results : 数据记录 */
	private List<T> content = new ArrayList<>();

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNo() {
		if (pageNo <= 0) {
			return 1;
		} else {
			return pageNo > pageCount ? pageCount : pageNo;
		}
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize <= 0 ? 10 : pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void resetPageNo() {
		pageNo = currentPage + 1;
		pageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
	}

}