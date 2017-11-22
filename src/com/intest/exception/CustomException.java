package com.intest.exception;

public class CustomException extends RuntimeException {

	/**
	 * 异常类
	 */
	private static final long serialVersionUID = 1L;
	private String exceptionCode;
	private String exceptionDesc;
	
	public CustomException(String exceptionCode, String exceptionDesc) {
		super();
		this.exceptionCode = exceptionCode;
		this.exceptionDesc = exceptionDesc;
		System.out.println("错误编号:" + this.exceptionDesc + " 错误描述:" + this.exceptionDesc);
	}
	public String getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	public String getExceptionDesc() {
		return exceptionDesc;
	}
	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}	
}
