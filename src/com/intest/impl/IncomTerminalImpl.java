package com.intest.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.intest.common.WuzhengSession;
import com.intest.bean.IncomTerminal;
import com.intest.dao.IncomTerminalDao;

public class IncomTerminalImpl implements IncomTerminalDao {

	@Override
	public Boolean saveOrUpdate(IncomTerminal incomTerminal) {
		
		Session session = WuzhengSession.getSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		session.saveOrUpdate(incomTerminal);
		transaction.commit();
		session.close();
		return true;
	}
	
	public static void main(String[] args) {
		IncomTerminal incomTerminal = new IncomTerminal();
		incomTerminal.setTc(201704221);
		incomTerminal.setIsOnline(1);
		incomTerminal.setGlobalFlag(1);
		new IncomTerminalImpl().saveOrUpdate(incomTerminal);
	}
}
