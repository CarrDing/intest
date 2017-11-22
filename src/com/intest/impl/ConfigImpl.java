package com.intest.impl;

import org.hibernate.Session;

import com.intest.bean.Config;
import com.intest.common.AppIncomSession;
import com.intest.dao.ConfigDao;

public class ConfigImpl implements ConfigDao {

	@Override
	public Config get(String configName) {
		Session session = AppIncomSession.getSession();
		Config config = (Config)session.createQuery("from Config where name=?")
				.setParameter(0, configName).uniqueResult();
		session.close();
		return config;
	}
}
