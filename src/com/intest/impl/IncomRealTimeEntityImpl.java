package com.intest.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.intest.bean.IncomRealTimeEntity;
import com.intest.common.WuzhengSession;
import com.intest.dao.IncomRealTimeEntityDao;

public class IncomRealTimeEntityImpl implements IncomRealTimeEntityDao {

	@Override
	public Boolean saveOrUpdate(IncomRealTimeEntity incomRealTimeEntity) {
		
		Session session = WuzhengSession.getSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		session.saveOrUpdate(incomRealTimeEntity);
		transaction.commit();
		session.close();
		return true;
	}	

	@Override
	public Boolean saveOrUpdate(List<IncomRealTimeEntity> incomRealTimeEntitys) {
		Session session = WuzhengSession.getSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		for (IncomRealTimeEntity incomRealTimeEntity : incomRealTimeEntitys) {
			session.saveOrUpdate(incomRealTimeEntity);
		}
		transaction.commit();
		session.close();
		return true;
	}

	public static void main(String[] args) {
		List<IncomRealTimeEntity> incomRealTimeEntitys = new ArrayList<IncomRealTimeEntity>();
		IncomRealTimeEntity realTimeEntity = new IncomRealTimeEntity();
		realTimeEntity.setTc(201704221);
		realTimeEntity.setTe(new Date());
		realTimeEntity.setMd5("1F15AB7B9BCBEA5D54883863A2FFAD8C");
		realTimeEntity.setLongitude(114.332955);
		realTimeEntity.setLatitude(30.5074025);
		realTimeEntity.setAltitude(24.0);
		realTimeEntity.setSpeed(0.0);
		realTimeEntity.setMileage(300.00);
		realTimeEntity.setDirection(0.0);
		realTimeEntity.setIsDriver(1);
		realTimeEntity.setIsPosition(1);
		incomRealTimeEntitys.add(realTimeEntity);
		new IncomRealTimeEntityImpl().saveWithJDBC(incomRealTimeEntitys);
	}

	@Override
	public Boolean saveWithJDBC(List<IncomRealTimeEntity> incomRealTimeEntitys) {
		String sql = "MERGE INTO INCOM_REALTIMEENTITY T1 USING (SELECT ? TERMINALCODE FROM DUAL WHERE ROWNUM=1) T2 ON (T1.TERMINALCODE = T2.TERMINALCODE) WHEN MATCHED THEN UPDATE SET TRAVELTIME=?, LONGITUDE=?, LATITUDE=?, ALTITUDE=?, SPEED=?, MILEAGE=?, DIRECTION=?, ISGPSPOSITION=?, ISDRIVING=? WHEN NOT MATCHED THEN INSERT (TERMINALCODE, TRAVELTIME, MD5CODE, LONGITUDE, LATITUDE, ALTITUDE, SPEED, MILEAGE, DIRECTION, ISGPSPOSITION, ISDRIVING) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = null;
	    PreparedStatement prepareStatement = null;
	    ResultSet resultSet = null;
	    try{
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        String url = "jdbc:oracle:thin:@192.168.1.79:1521:acid";
	        String username = "wuzheng_platform";
	        String password = "intest";
	        connection = DriverManager.getConnection(url, username, password);
	        long start = System.currentTimeMillis();
	        prepareStatement = connection.prepareStatement(sql);
	        for (IncomRealTimeEntity incomRealTimeEntity : incomRealTimeEntitys) {
	        	prepareStatement.setInt(1, incomRealTimeEntity.getTc());
	 	        prepareStatement.setTimestamp(2, new Timestamp(incomRealTimeEntity.getTe().getTime()));
	 	        prepareStatement.setDouble(3, incomRealTimeEntity.getLongitude());
	 	        prepareStatement.setDouble(4, incomRealTimeEntity.getLatitude());
	 	        prepareStatement.setDouble(5, incomRealTimeEntity.getAltitude());
	 	        prepareStatement.setDouble(6, incomRealTimeEntity.getSpeed());
	 	        prepareStatement.setDouble(7, incomRealTimeEntity.getMileage());
	 	        prepareStatement.setDouble(8, incomRealTimeEntity.getDirection());
	 	        prepareStatement.setDouble(9, incomRealTimeEntity.getIsDriver());
	 	        prepareStatement.setDouble(10, incomRealTimeEntity.getIsPosition());
	 	        prepareStatement.setInt(11, incomRealTimeEntity.getTc());
	 	        prepareStatement.setTimestamp(12, new Timestamp(incomRealTimeEntity.getTe().getTime()));
	 	        prepareStatement.setString(13, incomRealTimeEntity.getMd5());
	 	        prepareStatement.setDouble(14, incomRealTimeEntity.getLongitude());
	 	        prepareStatement.setDouble(15, incomRealTimeEntity.getLatitude());
	 	        prepareStatement.setDouble(16, incomRealTimeEntity.getAltitude());
	 	        prepareStatement.setDouble(17, incomRealTimeEntity.getSpeed());
	 	        prepareStatement.setDouble(18, incomRealTimeEntity.getMileage());
	 	        prepareStatement.setDouble(19, incomRealTimeEntity.getDirection());
	 	        prepareStatement.setDouble(20, incomRealTimeEntity.getIsDriver());
	 	        prepareStatement.setDouble(21, incomRealTimeEntity.getIsPosition());
	 	        prepareStatement.addBatch();
			}
	        prepareStatement.executeBatch();
	        connection.commit(); // 提交事务
	        long end  = System.currentTimeMillis();
	        System.out.println("用时:" + (end - start));
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    finally
	    {
	        try
	        {
	            if (resultSet != null)
	            	resultSet.close();
	            if (prepareStatement != null)
	            	prepareStatement.close();
	            if (connection != null)
	            	connection.close();
	            System.out.println("connection is closed");
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
		return true;
	}
}
