package com.huatek.core.dao.iml;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.RowMapper;

import com.huatek.core.contants.SymbolConstats;
import com.huatek.core.enums.QuerySqlTypeEnum;
import com.huatek.core.vo.QueryParam;
import com.huatek.core.vo.QueryPlaceholder;
import com.huatek.core.dao.iml.BaseDaoImpl;
import com.huatek.core.dao.BaseDao;
import com.huatek.core.vo.PageData;
import com.huatek.core.vo.QueryPage;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T,PK extends Serializable> implements BaseDao<T, PK> {

	/** @Fields COUNT_SQL : 总数SQL */
	private String COUNT_SQL = "";

	/** @Fields PAGE_SQL : 分页SQL */
	private String PAGE_SQL = "";

	/** @Fields ALL_SQL : 查询全部SQL */
	private String ALL_SQL = "";

	/** @Fields DELETE_SQL : 删除SQL */
	private String DELETE_SQL = "";

	/** @Fields FIDN_PK_SQL : 根据主键获取 */
	private String FIDN_PK_SQL = "";

	/** @Fields PK_NAME : 主键 */
	private String PK_NAME = null;

	/** @Fields FlUSH_COUNT : 批量保存提交的条数 */
	private static final Integer FlUSH_COUNT = 100;
	
	private Class<T> persistentClass;
	
	@Resource
	private SessionFactory sessionFactory;
	
	private Logger log = Logger.getLogger(BaseDaoImpl.class);
	
	
	public BaseDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		this.COUNT_SQL = "SELECT COUNT(*) FROM " + this.persistentClass.getName() + " WHERE 1=1 ";
		this.PAGE_SQL = " FROM " + this.persistentClass.getName() + " WHERE 1=1 ";
		this.ALL_SQL = " FROM " + this.persistentClass.getName() + " WHERE 1=1 ";
		Field[] fields = this.persistentClass.getDeclaredFields();
		for (Field f : fields) {
			if (f.isAnnotationPresent(Id.class)) {
				this.PK_NAME = f.getName();
			}
		}
		this.DELETE_SQL = " DELETE FROM " + this.persistentClass.getName() + " WHERE " + this.PK_NAME + " in (:ids)";
		this.FIDN_PK_SQL = " FROM " + this.persistentClass.getName() + " WHERE " + this.PK_NAME + "=:pk";
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Serializable save(T t) {
		
		return  getSession().save(t);
	}

	@Override
	public T load(PK pk) {
		@SuppressWarnings("unchecked")
		T load = (T) this.getSession().load(persistentClass, pk);
		return load;
	}

	@Override
	public T get(PK pk) {
		T load = (T) this.getSession().get(persistentClass, pk);
		return load;
	}

	@Override
	public boolean contains(T t) {
	
		return this.getSession().contains(t);
	}

	@Override
	public T findByPk(PK pk) {
		Query query = this.getSession().createQuery(FIDN_PK_SQL);
		query.setParameter("pk", pk);
		return (T) query.uniqueResult();
	}

	@Override
	public void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
		
	}

	@Override
	public void delete(T t) {
		this.getSession().delete(t);
		
	}

	@Override
	public boolean deleteByPk(PK pk) {
		T t = this.get(pk);
		if (t == null) {
			return false;
		}
		this.delete(t);
		return true;
	}

	@Override
	public void refresh(T t) {
		this.getSession().refresh(t);
		
	}

	@Override
	public void update(T t) {
		this.getSession().update(t);
		
	}

	@Override
	public void deleteAll(Collection<T> entities) {
		for (Object entity : entities) {
			this.getSession().delete(entity);
		}
	}

	@Override
	public Integer updateHql(String hql, Object... values) {
		Query query = this.getSession().createQuery(hql);
		this.setQueryParam(query, values);
		return query.executeUpdate();
	}

	@Override
	public Integer updateHql(String hql, Map<String, Object> paramMap) {
		Query query = this.getSession().createQuery(hql);
		this.setQueryParam(query, paramMap);
		return query.executeUpdate();
	}

	@Override
	public Integer updateSql(String sql, Object... values) {
		Query query = this.getSession().createSQLQuery(sql);
		this.setQueryParam(query, values);
		return query.executeUpdate();
	}

	@Override
	public T getUniqueByHQL(String hql, Object... values) {
		Query query = this.getSession().createQuery(hql);
		query.setCacheable(true);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
				;
			}
		}
		return (T) query.uniqueResult();
	}

	@Override
	public T getUniqueByHQL(String hql, Map<String, Object> paramMap) {
		Query query = this.getSession().createQuery(hql);
		query.setCacheable(true);
		this.setQueryParam(query, paramMap);
		return (T) query.uniqueResult();
	}

	@Override
	public T getUniqueBySQL(String sql, Object... values) {
		Query query = this.getSession().createSQLQuery(sql);
		query.setCacheable(true);
		this.setQueryParam(query, values);
		return (T) query.uniqueResult();
	}

	@Override
	public T getUniqueBySQL(String sql, Map<String, Object> paramMap) {
		Query query = this.getSession().createSQLQuery(sql);
		query.setCacheable(true);
		this.setQueryParam(query, paramMap);
		return (T) query.uniqueResult();
	}

	@Override
	public List<T> getListByHQL(String hql, Object... values) {
		Query query = this.getSession().createQuery(hql);
		query.setCacheable(true);
		this.setQueryParam(query, values);
		return query.list();
	}

	@Override
	public List<T> getListByHQL(String hql, Map<String, Object> paramMap) {
		Query query = this.getSession().createQuery(hql);
		query.setCacheable(true);
		this.setQueryParam(query, paramMap);
		return query.list();
	}

	@Override
	public List<T> getListBySQL(String sql, Object... values) {
		Query query = this.getSession().createSQLQuery(sql);
		query.setCacheable(true);
		this.setQueryParam(query, values);
		return query.list();
	}

	@Override
	public List<T> getListBySQL(String sql, Map<String, Object> paramMap) {
		Query query = this.getSession().createSQLQuery(sql);
		query.setCacheable(true);
		this.setQueryParam(query, paramMap);
		return query.list();
	}

	@Override
	public List<?> findListBySql(String sql, RowMapper<?> map, Object... values) {
		final List<Object> list = new ArrayList<>();
		// 执行JDBC的数据批量保存
		Work jdbcWork = new Work() {
			public void execute(Connection connection) throws SQLException {
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					ps = connection.prepareStatement(sql);
					for (int i = 0; i < values.length; i++) {
						setParameter(ps, i, values[i]);
					}

					rs = ps.executeQuery();
					int index = 0;
					while (rs.next()) {
						Object obj = map.mapRow(rs, index++);
						list.add(obj);
					}
				} finally {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
					}
				}
			}
		};
		this.getSession().doWork(jdbcWork);
		return list;
	}

	@Override
	public List<T> findAll() {
		Query query = this.getSession().createQuery(this.ALL_SQL);
		query.setCacheable(true);
		return query.list();
	}

	@Override
	public Long countByHql(String hql, Object... values) {
		Query query = this.getSession().createQuery(hql);
		this.setQueryParam(query, values);
		return (Long) query.uniqueResult();
	}

	@Override
	public PageData<T> findPage(QueryPage queryPage) {
		PageData<T> pageData = new PageData<T>();
		int currentPage = queryPage.getPage() > 1 ? queryPage.getPage() : 1;
		Long totalCount = this.queryTotalCount(queryPage);
		List<T> datalist = new ArrayList<>();
		if (0l != totalCount) {
			datalist = this.queryData(queryPage.getPageSize(), currentPage, queryPage);
		}
		pageData.setCurrentPage(currentPage);
		pageData.setPageSize(queryPage.getPageSize());
		pageData.setTotalCount(totalCount.intValue());
		pageData.resetPageNo();
		pageData.setContent(datalist);
		return pageData;
	}

	@Override
	public PageData<T> findPageByFetchedHql(String hql, String countHql,
			int pageNo, int pageSize, Object... values) {
		
		PageData<T> pageData = new PageData<T>();
		Query query = this.getSession().createQuery(hql);
		query.setCacheable(true);
		this.setQueryParam(query, values);
		int currentPage = pageNo > 1 ? pageNo : 1;
		pageData.setCurrentPage(currentPage);
		pageData.setPageSize(pageSize);
		if (countHql == null) {
			ScrollableResults results = query.scroll();
			results.last();
			pageData.setTotalCount(results.getRowNumber() + 1);// 设置总记录数
		} else {
			Long count = countByHql(countHql, values);
			pageData.setTotalCount(count.intValue());
		}
		pageData.resetPageNo();
		List<T> itemList = query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
		if (itemList == null) {
			itemList = new ArrayList<T>();
		}
		pageData.setContent(itemList);
		return pageData;
	}

	@Override
	public Map<String, Object> getParamsMap() {
		return new HashMap<>();
	}

	@Override
	public void deleteByPK(Collection<?> collections) {
		Query query = this.getSession().createQuery(DELETE_SQL);
		query.setParameterList("ids", collections);
		query.executeUpdate();
	}

	@Override
	public void saveBatch(List<T> entity, Integer fushCount) {
		for (int i = 0; i < entity.size(); i++) {
			save(entity.get(i));
			if (i != 0 && i % fushCount == 0) {
				getSession().flush();
				getSession().clear();
			}
		}
		
	}

	@Override
	public void saveBatch(List<T> entity) {
		saveBatch(entity, FlUSH_COUNT);
		
	}

	@Override
	public void updateBatch(List<T> entity) {
		updateBatch(entity, FlUSH_COUNT);
		
	}

	@Override
	public List<?> getListBySQL2(String sql, Map<String, Object> paramMap) {
		Query query = this.getSession().createSQLQuery(sql);
		query.setCacheable(true);
		this.setQueryParam(query, paramMap);
		return query.list();
	}

	@Override
	public List<Map<String, Object>> getListMap(String sql,
			Map<String, Object> paramMap) {
		Query query = this.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		this.setQueryParam(query, paramMap);
		return query.list();
	}

	@Override
	public void updateBatch(List<T> entitys, Integer flushCount) {
		for (int i = 0; i < entitys.size(); i++) {
			update(entitys.get(i));
			if (i != 0 && i % flushCount == 0) {
				getSession().flush();
				getSession().clear();
			}
		}
	}

	@Override
	public List<T> findlistByQueryPage(QueryPage queryPage) {
		StringBuilder builder = new StringBuilder(PAGE_SQL);
		List<QueryPlaceholder> holders = this.buildQueryConditons(builder, queryPage);
		this.buildOrderBy(builder, queryPage);
		Query query = this.getSession().createQuery(builder.toString());
		query.setCacheable(true);
		this.builderQueryConditonData(holders, query);
		List<T> dataList = query.list();
		return (List<T>) (dataList == null ? Collections.emptyList() : dataList);
	}

	@Override
	public List getListPageMap(String sql, Map<String, Object> paramMap,
			int page, int pageSize) {
		Query query = this.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		//query.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize);
		this.setQueryParam(query, paramMap);
		return query.list();
	}
	
	
	/**
	 * @Title: setQueryParam
	 * @Description: hql查询set值
	 * @createDate: 2017年4月1日 下午12:25:03
	 * @param paramMap
	 * @param query
	 */
	protected void setQueryParam(Query query, Map<String, Object> paramMap) {
		if (null != paramMap && !paramMap.isEmpty()) {
			Set<Entry<String, Object>> entrySet = paramMap.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				String key = entry.getKey();
				Object value = entry.getValue();
				if (value instanceof Object[]) {
					query.setParameterList(key, (Object[]) value);
				} else if (value instanceof Collection<?>) {
					query.setParameterList(key, (Collection<?>) value);
				} else if (value instanceof Date) {
					query.setTimestamp(key, (Date) value);
				} else {
					query.setParameter(key, value);
				}
			}
		}
	}
	
	
	/**
	 * @Title: setQueryParam
	 * @Description: 占位符查询操作 （不允许传递数组和集合）
	 * @createDate: 2017年4月1日 下午1:35:49
	 * @param query
	 * @param values
	 */
	private void setQueryParam(Query query, Object... values) {
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				Object obj = values[i];
				if (obj instanceof Object[] || obj instanceof Collection<?>) {

				}
				query.setParameter(i, values[i]);
			}
		}
	}
	
	/**
	 * 
	 * 设置每行批处理参数
	 * 
	 * @param ps
	 * @param pos
	 *            ?占位符索引，从0开始
	 * @param data
	 * @throws SQLException
	 * @see [类、类#方法、类#成员]
	 */
	private void setParameter(PreparedStatement ps, int pos, Object data) throws SQLException {
		if (data == null) {
			ps.setNull(pos + 1, Types.VARCHAR);
			return;
		}
		Class<? extends Object> dataCls = data.getClass();
		if (String.class.equals(dataCls)) {
			ps.setString(pos + 1, (String) data);
		} else if (boolean.class.equals(dataCls)) {
			ps.setBoolean(pos + 1, ((Boolean) data));
		} else if (int.class.equals(dataCls)) {
			ps.setInt(pos + 1, (Integer) data);
		} else if (double.class.equals(dataCls)) {
			ps.setDouble(pos + 1, (Double) data);
		} else if (Date.class.equals(dataCls)) {
			Date val = (Date) data;
			ps.setTimestamp(pos + 1, new Timestamp(val.getTime()));
		} else if (BigDecimal.class.equals(dataCls)) {
			ps.setBigDecimal(pos + 1, (BigDecimal) data);
		} else {
			// 未知类型
			ps.setObject(pos + 1, data);
		}
	}
	
	/**
	 * @Title: queryData
	 * @Description: 查询总数
	 * @createDate: 2017年3月8日 上午11:18:23
	 * @param queryPage
	 * @return Long
	 */
	private Long queryTotalCount(QueryPage queryPage) {
		StringBuilder builder = new StringBuilder(COUNT_SQL);
		List<QueryPlaceholder> holders = this.buildQueryConditons(builder, queryPage);
		Query query = this.getSession().createQuery(builder.toString());
		this.builderQueryConditonData(holders, query);
		query.setCacheable(true);
		return (Long) query.uniqueResult() == null ? 0l : (Long) query.uniqueResult() ;
	}
     
	/**
	 * @Title: queryData
	 * @Description: 查询分页数据
	 * @createDate: 2017年3月8日 下午2:07:40
	 * @param pageSize
	 * @param currentPage
	 * @param queryPage
	 * @return List<T>
	 */
	private List<T> queryData(int pageSize, int currentPage, QueryPage queryPage) {
		StringBuilder builder = new StringBuilder(PAGE_SQL);
		List<QueryPlaceholder> holders = this.buildQueryConditons(builder, queryPage);
		this.buildOrderBy(builder, queryPage);
		Query query = this.getSession().createQuery(builder.toString());
		query.setCacheable(true);
		this.builderQueryConditonData(holders, query);
		List<T> dataList = query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
		return (List<T>) (dataList == null ? Collections.emptyList() : dataList);
	}
	
	/**
	 * @Title: buildQueryConditons
	 * @Description: 创建查询条件
	 * @createDate: 2017年3月7日 下午6:13:24
	 * @param queryPage
	 * @param builder
	 * @return List<QueryPlaceholder>
	 */
	private List<QueryPlaceholder> buildQueryConditons(StringBuilder builder, QueryPage queryPage) {
		List<QueryParam> params = queryPage.getParams();
		List<QueryPlaceholder> holders = new ArrayList<>();
		if (!params.isEmpty()) {
			for (QueryParam p : params) {
				QueryPlaceholder holder = new QueryPlaceholder();
				String field = p.getField();
				String type = p.getType();
				Object value = p.queryValue();
				String logic = p.getLogic();
				if (StringUtils.isBlank(field) || null == value || StringUtils.isBlank(value.toString())) {
					continue;
				}
				String placeholder = SymbolConstats.COLON + field;
				if (logic.equalsIgnoreCase(QuerySqlTypeEnum.IN.getType())) {
					builder.append(SymbolConstats.SPACE + QuerySqlTypeEnum.AND.getType() + SymbolConstats.SPACE + field
							+ SymbolConstats.SPACE + logic + SymbolConstats.SPACE + SymbolConstats.lEFT_BRACE
							+ placeholder + SymbolConstats.RIGHT_BRACE + SymbolConstats.SPACE);
				} else {
					builder.append(SymbolConstats.SPACE + QuerySqlTypeEnum.AND.getType() + SymbolConstats.SPACE + field
							+ SymbolConstats.SPACE + logic + SymbolConstats.SPACE + SymbolConstats.lEFT_BRACE
							+ placeholder + SymbolConstats.RIGHT_BRACE + SymbolConstats.SPACE);
				}
				holder.setOperator(logic);
				holder.setPlaceholder(field);
				holder.setType(type);
				holder.setValue(value);
				holders.add(holder);
			}
		}
		return holders;
	}
	/**
	 * @Title: builderQueryConditonData
	 * @Description: 查询条件赋值
	 * @createDate: 2017年3月8日 下午2:06:13
	 * @param holders
	 * @param query
	 */
	private void builderQueryConditonData(List<QueryPlaceholder> holders, Query query) {
		if (!holders.isEmpty()) {
			for (QueryPlaceholder holder : holders) {
				String operator = holder.getOperator();
				Object value = holder.getValue();
				if (operator.equalsIgnoreCase(QuerySqlTypeEnum.IN.getType())) {
					if(value instanceof Object []){
						query.setParameterList(holder.getPlaceholder(), (Object []) value);
					}else if(value instanceof Collection){
						query.setParameterList(holder.getPlaceholder(), (Collection<?>) value);
					}
				} else if (operator.equalsIgnoreCase(QuerySqlTypeEnum.LIKE.getType())) {
					query.setParameter(holder.getPlaceholder(),
							SymbolConstats.SIGN + value.toString() + SymbolConstats.SIGN);
				}else if (operator.equalsIgnoreCase(QuerySqlTypeEnum.RIGHT_LIKE.getType())) {
					query.setParameter(holder.getPlaceholder(), value.toString() + SymbolConstats.SIGN);
				}else if (operator.equalsIgnoreCase(QuerySqlTypeEnum.LEFT_LIKE.getType())) {
					query.setParameter(holder.getPlaceholder(),   SymbolConstats.SIGN+value.toString() );
				} else {
					query.setParameter(holder.getPlaceholder(), value);
				}
			}
		}
	}
	

	/**
	 * @Title: buildOrderBy
	 * @Description: 构建排序
	 * @createDate: 2017年3月8日 下午4:02:31
	 * @param builder
	 * @param queryPage
	 */
	private void buildOrderBy(StringBuilder builder, QueryPage queryPage) {
		String oderbyType = queryPage.getOderbyType();
		String orderByField = queryPage.getOrderByField();
		// Table table = this.persistentClass.getAnnotation(Table.class);
		if (StringUtils.isBlank(orderByField)) {
			builder.append(SymbolConstats.SPACE + QuerySqlTypeEnum.ORDER_BY.getType() + SymbolConstats.SPACE + PK_NAME
					+ SymbolConstats.SPACE);
		} else {
			boolean flag = false;
			Field[] fields = persistentClass.getDeclaredFields();
			for (Field f : fields) {
				if (f.isAnnotationPresent(Column.class) && f.getName().equals(orderByField)) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				builder.append(SymbolConstats.SPACE + QuerySqlTypeEnum.ORDER_BY.getType() + SymbolConstats.SPACE
						+ PK_NAME + SymbolConstats.SPACE);
				log.error(persistentClass.getName() + "分页查询指定的排序字段(" + orderByField + ")在数据库中不存在......");
			} else {
				builder.append(SymbolConstats.SPACE + QuerySqlTypeEnum.ORDER_BY.getType() + SymbolConstats.SPACE
						+ orderByField + SymbolConstats.SPACE);
			}
		}

		if (StringUtils.isBlank(oderbyType)) {
			builder.append(SymbolConstats.SPACE + QuerySqlTypeEnum.DESC.getType() + SymbolConstats.SPACE);
		} else {
			if (oderbyType.equalsIgnoreCase(QuerySqlTypeEnum.DESC.getType())
					|| oderbyType.equalsIgnoreCase(QuerySqlTypeEnum.ASC.getType())) {
				builder.append(SymbolConstats.SPACE + oderbyType + SymbolConstats.SPACE);
			} else {
				log.error(persistentClass.getName() + "分页查询指定的排序类型(" + oderbyType + ")错误......");
				builder.append(SymbolConstats.SPACE + QuerySqlTypeEnum.DESC.getType() + SymbolConstats.SPACE);
			}
		}
	}

	
}
