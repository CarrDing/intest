package com.intest.dao;

import java.util.List;

import com.intest.bean.CarBaseInfo;
import com.intest.bean.Terminal;

public interface CallProcedureDao {
	
	public List<CarBaseInfo> queryCarBaseInfo();
	public List<Terminal> loadTerminal(Integer terminalcode);
}
