package com.intest.dao;

import java.util.List;

import com.intest.bean.IncomRealTimeEntity;

public interface IncomRealTimeEntityDao {

	public Boolean saveOrUpdate(IncomRealTimeEntity incomRealTimeEntity);
	public Boolean saveOrUpdate(List<IncomRealTimeEntity> incomRealTimeEntitys);
	public Boolean saveWithJDBC(List<IncomRealTimeEntity> incomRealTimeEntitys);
}
