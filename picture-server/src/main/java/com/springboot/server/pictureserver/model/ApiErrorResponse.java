package com.springboot.server.pictureserver.model;

public class ApiErrorResponse {
	
	private Integer code;
	private Integer status;
	private String message;
	public ApiErrorResponse() {
		super();
	}
	public ApiErrorResponse(Integer code, Integer status, String message) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ApiErrorResponse [code=" + code + ", status=" + status
				+ ", message=" + message + "]";
	}
	
}
