package com.lei.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.lei.dao.BaseDaoI;
import com.lei.model.GridModel;
import com.lei.utils.HibernateUtil;

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDaoI<T> {

	@Override
	public void save(T t) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public T get(String hql) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery(hql);
			List<T> lists= q.list();
			if (lists!=null && lists.size()>0) {
				return lists.get(0);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	@Override
	public T get(String hql, Map<String, Object> params) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			
			Query q = session.createQuery(hql);
			if (params!=null && !params.isEmpty()) {
				for (String o : params.keySet()) {
					q.setParameter(o, params.get(o));
				}
			}
			List<T> lists= q.list();
			if (lists!=null && lists.size()>0) {
				return lists.get(0);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	@Override
	public T get(Class<T> c, String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			return (T)session.get(c, id);
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	@Override
	public T get(String hql, Object[] params) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			
			Query q = session.createQuery(hql);
			if (params!=null && params.length>0) {
				for (int i = 0; i < params.length; i++) {
					q.setParameter(i, params[i]);
				}
			}
			List<T> lists= q.list();
			if (lists!=null && lists.size()>0) {
				return lists.get(0);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
		return null;
	}


	@Override
	public void saveOrUpdate(T o) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(o);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
	}

	
	@Override
	public void update(T o) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(o);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public void delete(T o) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(o);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
		
	}

	
	@Override
	public List<T> find(String hql) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			
			Query q = session.createQuery(hql);
			return q.list();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			
			Query q = session.createQuery(hql);
			if (params!=null && !params.isEmpty()) {
				for (String o : params.keySet()) {
					q.setParameter(o, params.get(o));
				}
			}
			return q.list();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			
			Query q = session.createQuery(hql);
			if (params!=null && !params.isEmpty()) {
				for (String o : params.keySet()) {
					q.setParameter(o, params.get(o));
				}
			}
				return q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	@Override
	public int count(String hql) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		int totalRows = 0;
		try {
			Query q = session.createQuery(hql);
			totalRows = (new Integer(q.uniqueResult().toString())).intValue();  
			return  totalRows;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}
	@Override
	public int count(String hql,Map<String, Object> params) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		int totalRows = 0;
		try {
			Query q = session.createQuery(hql);
			if (params!=null && !params.isEmpty()) {
				for (String o : params.keySet()) {
					q.setParameter(o, params.get(o));
				}
			}
			totalRows = (new Integer(q.uniqueResult().toString())).intValue();  
			return  totalRows;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}
	
	@Override
	public void executeHql(String hql) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery(hql);
			q.executeUpdate();
			tx.commit();
		} catch (Exception e) {
		}finally {
			HibernateUtil.closeSession();
		}
	}

}
