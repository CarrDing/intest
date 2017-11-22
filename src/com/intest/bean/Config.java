package com.intest.bean;

import java.sql.Clob;

public class Config {

	private Integer id;
	private String name;
	private String type;
	private Clob info;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Clob getInfo() {
		return info;
	}
	public void setInfo(Clob info) {
		this.info = info;
	}
}
