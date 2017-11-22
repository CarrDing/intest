package com.intest.dao;

import java.util.List;

import com.intest.bean.DataWareHouse;
import com.intest.bean.DataWareHouseV1;

public interface DataWareHouseDao {

	public Boolean save(DataWareHouse dataWareHouse);
	public Boolean saveOrUpdate(List<DataWareHouse> dataWareHouses);
	public Boolean saveWithJDBC(List<DataWareHouseV1> dataWareHouses);
}
