package com.tools.json2obj.common;

public class BaseException extends RuntimeException {
	private static final long	serialVersionUID	= -3578586837684902952L;
	
	protected int				code;
	
	public BaseException() {
	}
	
	public BaseException(String message) {
		super(message);
	}
	
	public BaseException(int code, String message) {
		super(message);
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
}
