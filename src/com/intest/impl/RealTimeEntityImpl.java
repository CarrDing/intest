package com.intest.impl;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.intest.bean.RealTimeEntity;
import com.intest.common.AppIncomSession;
import com.intest.dao.RealTimeEntityDao;

public class RealTimeEntityImpl implements RealTimeEntityDao {
	
	@Override
	public boolean saveOrUpdate(RealTimeEntity realTimeEntity) {
		Session session = AppIncomSession.getSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		session.saveOrUpdate(realTimeEntity);
		transaction.commit();
		session.close();
		return true;
	}
	
	public static void main(String[] args) {
		RealTimeEntity realTimeEntity = new RealTimeEntity();
		realTimeEntity.setTc(201704221);
		realTimeEntity.setTe(new Date());
		realTimeEntity.setLongitude(114.332955);
		realTimeEntity.setLatitude(30.5074025);
		realTimeEntity.setAltitude(24.0);
		realTimeEntity.setSpeed(0.0);
		realTimeEntity.setMileage(300.00);
		realTimeEntity.setDirection(0.0);
		new RealTimeEntityImpl().saveOrUpdate(realTimeEntity);
	}

}
