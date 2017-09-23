package com.lei.dao;

import java.util.List;
import java.util.Map;

import com.lei.model.GridModel;
import com.lei.model.User;

public interface BaseDaoI<T> {
	
	public void save(T t);
	
	public void saveOrUpdate(T o);
	
	public void update(T o);
	
	public void delete(T o);
	/**
	 * 得到一个对象
	 * hql需要自己写全
	 * @param hql
	 * @return
	 */
	public T get(String hql);
	
	/**
	 * 得到一个对象
	 * hql =from Tuser t where t.username=:username and t.password=:password
	 * @param hql
	 * @param params
	 * @return
	 */
	public T get(String hql,Map<String, Object> params);
	
	public T get(String hql,Object[] params);
	
	public T get(Class<T> c, String id);
	
	
	/**
	 * 返回所有內容
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);
	
	public List<T> find(String hql,Map<String, Object> params);
	/**
	 * 分页查询
	 * @param hql
	 * @param params
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql,Map<String, Object> params,int page,int rows);
	
	public int count(String hql);
	public int count(String hql,Map<String, Object> m);
	
	public void executeHql(String hql);
	
}
