package com.intest.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.intest.bean.CarBaseInfo;
import com.intest.bean.Terminal;
import com.intest.constant.Constant;
import com.intest.dao.CallProcedureDao;
import com.intest.util.PropertiesUtil;

public class CallProcedureImpl implements CallProcedureDao {

	@Override
	public List<CarBaseInfo> queryCarBaseInfo() {
		List<CarBaseInfo> list = new ArrayList<CarBaseInfo>();
		// 定义需要的变量
        Connection ct = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            // 加载驱动
            Class.forName(PropertiesUtil.getProperties(Constant.DRIVER));
            // 得到连接
            ct = DriverManager.getConnection(PropertiesUtil.getProperties(Constant.URL), PropertiesUtil.getProperties(Constant.O_USER_NAME), PropertiesUtil.getProperties(Constant.O_PASS_WORD));
            // 创建CallableStatement接口
            cs = ct.prepareCall("{call INCOM.QueryCarBaseInfo(?)}");
            // 给out?注册
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            // 执行
            cs.execute();

            // 这里是关键所在，java没有接收结果集的get方法，所以只能用getObject来接收结果集，接收到后需要使用ResultSet强转才可以
            rs = (ResultSet) cs.getObject(1);
            // 循环取出
            while (rs.next()) {
                CarBaseInfo terminal = new CarBaseInfo();
                terminal.setTerminalcode(rs.getInt("TERMINALCODE"));
                terminal.setVinno(rs.getString("VINNO"));
                terminal.setIsBinding(rs.getInt("ISBINDING"));
                terminal.setPhoneCode(rs.getString("PHONECODE"));
                terminal.setForwardState(rs.getString("FORWARDSTATE"));
                terminal.setIfissude(rs.getString("IFISSUED"));
                list.add(terminal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ct != null) {
                    ct.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            cs = null;
            rs = null;
            ct = null;
        }
		return list;
	}
	
	public static void main(String[] args) {
		System.out.println(new CallProcedureImpl().loadTerminal(201704221).size());
	}

	@Override
	public List<Terminal> loadTerminal(Integer terminalcode) {

		List<Terminal> list = new ArrayList<Terminal>();
		// 定义需要的变量
        Connection ct = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            // 加载驱动
            Class.forName(PropertiesUtil.getProperties(Constant.DRIVER));
            // 得到连接
            ct = DriverManager.getConnection(PropertiesUtil.getProperties(Constant.URL), PropertiesUtil.getProperties(Constant.O_USER_NAME), PropertiesUtil.getProperties(Constant.O_PASS_WORD));
            // 创建CallableStatement接口
            cs = ct.prepareCall("{call INCOM.LoadTerminal(?,?)}");
            // 给out?注册
            cs.setInt(1, terminalcode);
            cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            // 执行
            cs.execute();

            // 这里是关键所在，java没有接收结果集的get方法，所以只能用getObject来接收结果集，接收到后需要使用ResultSet强转才可以
            rs = (ResultSet) cs.getObject(2);
            // 循环取出
            while (rs.next()) {
                Terminal terminal = new Terminal();
                terminal.setTerminalCode(rs.getInt("TERMINALCODE"));
                terminal.setBarcode(rs.getString("BARCODE"));
                terminal.setCurrentdbcfilename(rs.getString("CURRENTDBCFILENAME"));
                terminal.setDbseq(rs.getString("DBSEQ"));
                terminal.setFirmware(rs.getString("FIRMWARE"));
                terminal.setIsbinding(rs.getString("ISBINDING"));
                list.add(terminal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ct != null) {
                    ct.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            cs = null;
            rs = null;
            ct = null;
        }
		return list;
	}
}
