package com.intest.bean;

public class CarBaseInfo {

	private Integer terminalcode;
	private String vinno;
	private Integer IsBinding;
	private String PhoneCode;
	private String ForwardState;
	private String ifissude;
	
	public Integer getTerminalcode() {
		return terminalcode;
	}
	public void setTerminalcode(Integer terminalcode) {
		this.terminalcode = terminalcode;
	}
	public String getVinno() {
		return vinno;
	}
	public void setVinno(String vinno) {
		this.vinno = vinno;
	}
	public Integer getIsBinding() {
		return IsBinding;
	}
	public void setIsBinding(Integer isBinding) {
		IsBinding = isBinding;
	}
	public String getPhoneCode() {
		return PhoneCode;
	}
	public void setPhoneCode(String phoneCode) {
		PhoneCode = phoneCode;
	}
	public String getForwardState() {
		return ForwardState;
	}
	public void setForwardState(String forwardState) {
		ForwardState = forwardState;
	}
	public String getIfissude() {
		return ifissude;
	}
	public void setIfissude(String ifissude) {
		this.ifissude = ifissude;
	}
}
