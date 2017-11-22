package com.intest.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class WuzhengSession {

	/*
	 * 创建session工厂 单态模式
	 */
	private static SessionFactory sessionFactory = new Configuration().configure("/hibernatewz.cfg.xml").buildSessionFactory();

	public static Session getSession() {
		return sessionFactory.openSession();
	}
}
