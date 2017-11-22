package com.intest.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AppIncomSession {

	/*
	 * 创建session工厂 单态模式
	 */
	public static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	public static Session getSession() {
		return sessionFactory.openSession();
	}
}
