package com.intest.impl;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.intest.bean.RealTimeStatusEntity;
import com.intest.common.AppIncomSession;
import com.intest.dao.RealTimeStatusEntityDao;

public class RealTimeStatusEntityImpl implements RealTimeStatusEntityDao {

	@Override
	public Boolean saveOrUpdate(RealTimeStatusEntity realTimeStatusEntity) {
		Session session = AppIncomSession.getSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		session.saveOrUpdate(realTimeStatusEntity);
		transaction.commit();
		session.close();
		return true;
	}
}
