package com.intest.bean;

import java.util.Date;
import java.util.List;

public class DataWareHouseV1 {

	private String seqId;
	private Integer tc;
	private Date te;
	private String md5;
	private Double longitude;
	private Double latitude;
	private Double altitude;
	private Double speed;
	private Double mileage;
	private Double direction;
	private List<Double> veliData;
	
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
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
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}	
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
	public List<Double> getVeliData() {
		return veliData;
	}
	public void setVeliData(List<Double> veliData) {
		this.veliData = veliData;
	}
	@Override
	public boolean equals(Object obj) {
		DataWareHouseV1 dataWareHouseV1 = (DataWareHouseV1) obj;
		if (dataWareHouseV1.getSeqId().equals(this.getSeqId())) {
			return true;
		} else {
			return false;
		}
	}
}
