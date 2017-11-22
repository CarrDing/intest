package com.intest.bean;

public class Terminal {

	private String barcode;
	private Integer terminalCode;
	private String firmware;
	private String currentdbcfilename;
	private String dbseq;
	private String isbinding;
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Integer getTerminalCode() {
		return terminalCode;
	}
	public void setTerminalCode(Integer terminalCode) {
		this.terminalCode = terminalCode;
	}
	public String getFirmware() {
		return firmware;
	}
	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}
	public String getCurrentdbcfilename() {
		return currentdbcfilename;
	}
	public void setCurrentdbcfilename(String currentdbcfilename) {
		this.currentdbcfilename = currentdbcfilename;
	}
	public String getDbseq() {
		return dbseq;
	}
	public void setDbseq(String dbseq) {
		this.dbseq = dbseq;
	}
	public String getIsbinding() {
		return isbinding;
	}
	public void setIsbinding(String isbinding) {
		this.isbinding = isbinding;
	}
}
