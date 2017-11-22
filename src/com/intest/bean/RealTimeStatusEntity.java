package com.intest.bean;

import java.util.Date;

public class RealTimeStatusEntity {

	/**
	 * 终端号
	 * 数据接收时间
	 * 是否行驶
	 * 是否定位
	 */
	private Integer tc;
	private Date te;
	private Integer isDriver;
	private Integer isPosition;
	
	public Integer getTc() {
		return tc;
	}
	public void setTc(Integer tc) {
		this.tc = tc;
	}
	public Date getTe() {
		return te;
	}
	public void setTe(Date te) {
		this.te = te;
	}
	public Integer getIsDriver() {
		return isDriver;
	}
	public void setIsDriver(Integer isDriver) {
		this.isDriver = isDriver;
	}
	public Integer getIsPosition() {
		return isPosition;
	}
	public void setIsPosition(Integer isPosition) {
		this.isPosition = isPosition;
	}
}