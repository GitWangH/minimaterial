/**
 * 
 */
package com.huatek.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.huatek.core.vo.PageData;
import com.huatek.core.vo.QueryPage;

/**
 * @ClassName: BaseDao
 * @FullClassPath: com.lpp.mq.dao.BaseDao
 * @Description: Dao封装接口
 * @author: Arno
 * @date: 2017年3月4日 下午5:58:53
 * @version: 1.0
 */

public interface BaseDao<T, PK> {

	/**
	 * @Title: save
	 * @Description: <保存实体>
	 * @createDate: 2017年3月6日 上午11:18:08
	 * @param t
	 *            实体参数
	 * @return Serializable
	 */
	public abstract Serializable save(T t);
	

	/**
	 * @Title: load
	 * @Description: <加载实体的load方法>
	 * @createDate: 2017年3月6日 上午11:20:40
	 * @param pk
	 *            实体的pk主键
	 * @return 查询出来的实体
	 */
	public abstract T load(PK pk);

	/**
	 * @Title: get
	 * @Description: <查找的get方法>
	 * @createDate: 2017年3月6日 上午11:21:24
	 * @param pk
	 *            实体的pk主键
	 * @return 查询出来的实体
	 */
	public abstract T get(PK pk);
	
	
	

	/**
	 * @Title: contains
	 * @Description: 是否包含
	 * @createDate: 2017年3月6日 上午11:21:44
	 * @param t
	 *            实体参数
	 * @return boolean
	 */
	public abstract boolean contains(T t);

	
	/**
	 * @Title: findByPk
	 * @Description: <根据主键删除获取>
	 * @createDate: 2017年3月6日 上午11:22:13
	 * @param pk
	 *            主键
	 * @return boolean
	 */
	public abstract T findByPk(PK pk);
	
	
	/**
	 * @Title: saveOrUpdate
	 * @Description: <保存或者更新实体>
	 * @createDate: 2017年3月6日 上午11:18:23
	 * @param t
	 *            实体参数
	 */
	public abstract void saveOrUpdate(T t);
	
	
	/**
	 * @Title: delete
	 * @Description: <删除表中的数据>
	 * @createDate: 2017年3月6日 上午11:22:00
	 * @param t
	 *            实体参数
	 */
	public abstract void delete(T t);

	/**
	 * @Title: deleteByPk
	 * @Description: <根据主键删除数据>
	 * @createDate: 2017年3月6日 上午11:22:13
	 * @param pk
	 *            主键
	 * @return boolean
	 */
	public abstract boolean deleteByPk(PK pk);

	/**
	 * @Title: refresh
	 * @Description: 刷新
	 * @createDate: 2017年3月6日 上午11:31:34
	 * @param t
	 *            实体
	 */
	public abstract void refresh(T t);

	/**
	 * @Title: update
	 * @Description: 更新
	 * @createDate: 2017年3月6日 上午11:31:15
	 * @param t
	 *            实体
	 */
	public abstract void update(T t);

	/**
	 * @Title: deleteAll
	 * @Description: <删除所有>
	 * @createDate: 2017年3月6日 上午11:22:53
	 * @param entities
	 *            实体的Collection集合
	 */
	public abstract void deleteAll(Collection<T> entities);

	/**
	 * @Title: updateHql
	 * @Description: <执行Hql语句>
	 * @createDate: 2017年3月6日 上午11:23:15
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            不定参数数组
	 * @return Integer
	 */
	public abstract Integer updateHql(String hql, Object... values);

	
	/**
	 * @Title: updateHql
	 * @Description: <执行Hql语句>
	 * @createDate: 2017年3月6日 上午11:23:15
	 * @param hql
	 *            HQL语句
	 * @param paramMap
	 *           map
	 * @return Integer
	 */
	public Integer updateHql(String hql, Map<String, Object> paramMap);
	
	/**
	 * @Title: updateSql
	 * @Description: <执行Sql语句>
	 * @createDate: 2017年3月6日 上午11:23:53
	 * @param sql
	 *            SQL语句
	 * @param values
	 *            不定参数数组
	 * @return Integer
	 */
	public abstract Integer updateSql(String sql, Object... values);

	/**
	 * @Title: getByHQL
	 * @Description: <根据HQL语句查找唯一实体>
	 * @createDate: 2017年3月6日 上午11:24:13
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            不定参数的Object数组
	 * @return 查询实体
	 */
	public abstract T getUniqueByHQL(String hql, Object... values);

	/**
	 * @Title: getByHQL
	 * @Description: <根据HQL语句查找唯一实体>
	 * @createDate: 2017年3月6日 上午11:24:13
	 * @param hql
	 *            HQL语句
	 * @param paramMap
	 *            参数MAP
	 * @return 查询实体
	 */
	public abstract T getUniqueByHQL(String hql, Map<String, Object> paramMap);

	/**
	 * @Title: getUniqueBySQL
	 * @Description: <根据SQL语句查找唯一实体>
	 * @createDate: 2017年3月6日 上午11:26:15
	 * @param sql
	 *            SQL语句
	 * @param values
	 *            不定参数的Object数组
	 * @return 查询实体
	 */
	public abstract T getUniqueBySQL(String sql, Object... values);

	/**
	 * @Title: getUniqueBySQL
	 * @Description: <根据SQL语句查找唯一实体>
	 * @createDate: 2017年3月6日 上午11:26:15
	 * @param sql
	 *            SQL语句
	 * @param paramMap
	 *            参数MAP
	 * @return 查询实体
	 */
	public abstract T getUniqueBySQL(String sql, Map<String, Object> paramMap);

	/**
	 * @Title: getListByHQL
	 * @Description: <根据HQL语句，得到对应的list>
	 * @createDate: 2017年3月6日 上午11:29:22
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            不定参数的Object数组
	 * @return 查询多个实体的List集合
	 */
	public abstract List<T> getListByHQL(String hql, Object... values);

	/**
	 * @Title: getListByHQL
	 * @Description: <根据HQL语句，得到对应的list>
	 * @createDate: 2017年3月6日 上午11:29:22
	 * @param hql
	 *            HQL语句
	 * @param paramMap
	 *            参数map
	 * @return 查询多个实体的List集合
	 */
	public abstract List<T> getListByHQL(String hql, Map<String, Object> paramMap);

	/**
	 * @Title: getListBySQL
	 * @Description: <根据SQL语句，得到对应的list>
	 * @createDate: 2017年3月6日 上午11:29:40
	 * @param sql
	 *            SQL语句
	 * @param values
	 *            不定参数的Object数组
	 * @return 查询多个实体的List集合
	 */
	public abstract List<T> getListBySQL(String sql, Object... values);

	/**
	 * @Title: getListBySQL
	 * @Description: <根据SQL语句，得到对应的list>
	 * @createDate: 2017年3月6日 上午11:29:40
	 * @param sql
	 *            SQL语句
	 * @param paramMap
	 *            参数map
	 * @return 查询多个实体的List集合
	 */
	public abstract List<T> getListBySQL(String sql, Map<String, Object> paramMap);

	/**
	 * @Title: getListBySQL
	 * @param sql
	 * @param map
	 * @param values
	 * @return List
	 */
	public List<?> findListBySql(final String sql, final RowMapper<?> map, final Object... values);
	
	
	/** 
	* @Title: findAll 
	* @Description: 查询全部 
	* @createDate: 2017年3月21日 下午6:26:42
	* @return List<T>
	*/ 
	public List<T> findAll();
	

	/**
	 * @Title: getListBySQL <根据HQL得到记录数>
	 * 
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            不定参数的Object数组
	 * @return 记录总数
	 */
	public abstract Long countByHql(String hql, Object... values);

	/**
	 * @Title: findPage
	 * 
	 * @Description: <HQL分页查询>
	 * 
	 * @createDate: 2017年3月7日 下午5:46:43
	 * 
	 * @param queryPage
	 *            查询参数
	 * @return PageData的封装类，里面包含了页码的信息以及查询的数据List集合
	 */
	public PageData<T> findPage( QueryPage queryPage);

	/**
	 * @Title: getListBySQL <HQL分页查询>
	 * 
	 * @param hql
	 *            HQL语句
	 * @param countHql
	 *            查询记录条数的HQL语句
	 * @param pageNo
	 *            下一页
	 * @param pageSize
	 *            一页总条数
	 * @param values
	 *            不定Object数组参数
	 * @return PageData的封装类，里面包含了页码的信息以及查询的数据List集合
	 */
	public abstract PageData<T> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize,
			Object... values);

	/** 
	* @Title: getParamsMap 
	* @Description: 获取map 
	* @createDate: 2017年3月21日 下午6:38:54
	* @return  Map<String, Object> 
	*/ 
	public Map<String, Object> getParamsMap ();

	/** 
	* @Title: deleteByPK 
	* @Description: 根据主键删除(多选删除) 
	* @createDate: 2017年3月24日 下午2:01:34
	* @param collections 
	*/ 
	void deleteByPK(Collection<?> collections);

	
	/** 
	* @Title: saveBatch 
	* @Description: 批量保存 
	* @createDate: 2017年4月1日 上午10:39:07
	* @param entity
	* @param fushCount 
	*/ 
	public abstract void saveBatch(List<T> entity, Integer fushCount );
	
	/** 
	* @Title: saveBatch 
	* @Description:  批量保存  
	* @createDate: 2017年4月1日 上午10:40:16
	* @param entity 
	*/ 
	public abstract void saveBatch(List<T> entity);
	
	/** 
	* @Title: updateBatch 
	* @Description:  批量保存  
	* @createDate: 2017年4月1日 上午10:40:16
	* @param entity 
	*/ 
	public abstract void updateBatch(List<T> entity);
	
	
	/** 
	* @Title: getListBySQL2 
	* @Description: SQL 查询返回查询列 
	* @createDate: Nov 9, 2017 2:07:49 PM
	* @param sql
	* @param paramMap
	* @return List<?>
	*/ 
	public List<?> getListBySQL2 (String sql, Map<String, Object> paramMap);
	
	/** 
	* @Title: getListMap 
	* @Description: SQL 查询返回查询列  
	* @createDate: Nov 9, 2017 2:08:13 PM
	* @param sql
	* @param paramMap
	* @return 
	*/ 
	public List<Map<String,Object>> getListMap(String sql, Map<String, Object> paramMap);
	

	/** 
	* @Title: updateBatch 
	* @Description: 批量修改 
	* @createDate: Oct 17, 2017 8:07:09 PM
	* @param entitys
	* @param flushCount 
	*/ 
	void updateBatch(List<T> entitys, Integer flushCount);
	
	/** 
	* @Title: findlistByQueryPage 
	* @Description: 查询全部 
	* @createDate: 2017年3月21日 下午6:26:42
	* @return List<T>
	*/ 
	public List<T> findlistByQueryPage(QueryPage queryPage);



	/** 
	* @Title: getListPageMap 
	* @Description: TODO 
	* @createDate: Mar 16, 2018 7:06:36 PM
	* @param sql
	* @param paramMap
	* @param page
	* @param pageSize
	* @return 
	*/ 
	List getListPageMap(String sql, Map<String, Object> paramMap, int page, int pageSize);
}
