package com.intest.bean;

import java.util.Date;

public class RealTimeEntity {

	private Integer tc;
	private Date te;
	/*private String seq;
	private boolean upandDown;
	private Timestamp time;
	private String iccid;
	private String imei;
	private String tcType;
	private String vol;
	private String word;
	private String ant;*/
	private Double longitude;
	private Double latitude;
	private Double altitude;
	private Double speed;
	private Double mileage;
	private Double direction;
	
	
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getAltitude() {
		return altitude;
	}
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public Double getMileage() {
		return mileage;
	}
	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}
	public Double getDirection() {
		return direction;
	}
	public void setDirection(Double direction) {
		this.direction = direction;
	}
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
	
}
