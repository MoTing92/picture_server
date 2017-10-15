package com.springboot.server.pictureserver.model;

public class ResultVo {

	private int errorCode;
	private String errorMsg;
	private Integer total;
	private Object data;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public void setError(int code, String msg) {
		this.errorCode = code;
		this.errorMsg = msg;
		
	}
	
}
