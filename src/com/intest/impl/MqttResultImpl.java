package com.intest.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.intest.bean.MqttResult;
import com.intest.common.AppIncomSession;

public class MqttResultImpl {

	public Boolean update(MqttResult mqttResult) {
		Session session = AppIncomSession.getSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		session.saveOrUpdate(mqttResult);
		transaction.commit();
		session.close();
		return true;
	}
	
	public void update(String result) {
		MqttResult mqttResult = new MqttResult();
		mqttResult.setResult(result);
		mqttResult.setId(1);
		new MqttResultImpl().update(mqttResult);
	}
	
	public String find(Integer id) {
		Session session = AppIncomSession.getSession();
		MqttResult mqttResult = (MqttResult) session.createQuery("from MqttResult where id = ?").setParameter(0, id).uniqueResult();
		return mqttResult.getResult();
	}
	
	public static void main(String[] args) {
		System.out.println(new MqttResultImpl().find(1));
	}
}
