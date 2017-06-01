package com.ll.dd.exception;

public class OApiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer errCode = 0;

	private String errMsg = "";

	public OApiException(int errCode, String errMsg) {
		super("error code: " + errCode + ", error message: " + errMsg);
		this.errMsg = errMsg;
		this.errCode = errCode;
	}

	public Integer getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
