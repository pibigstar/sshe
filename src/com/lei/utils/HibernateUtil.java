package com.lei.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	private static final ThreadLocal<Session> THREADLOACL_LOCAL = new ThreadLocal<>();
	
	//使用静态代码块初始化Hibernate
	static{
		try{
			Configuration cfg = new Configuration().configure();
			sessionFactory = cfg.buildSessionFactory();
		}catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}
	
	//获得SessionFactory实例
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	//通过ThreadLocal对象管理Session实例
	public static Session getSession(){
		Session session =THREADLOACL_LOCAL.get();
		if (session==null || !session.isOpen()) {
			rebuildSessionFactory();
		}
		//通过sessionfactory对象创建session对象
		session = (sessionFactory!=null)?sessionFactory.openSession():null;
		
		//将新打开的session对象保存到threadLocal中
		THREADLOACL_LOCAL.set(session);
		return session;
	}
	
	
	//关闭Session 实例
	public static void closeSession(){
		Session session = THREADLOACL_LOCAL.get();
		
		THREADLOACL_LOCAL.set(null);
		if (session!=null) {
			session.close();
		}
	}
	
	//重建SessionFactory
	public static void rebuildSessionFactory(){
		try {
			
		sessionFactory = new Configuration().configure().buildSessionFactory();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//关闭缓存连接池
	public static void shutdown(){
		getSessionFactory().close();
	}
		
}
