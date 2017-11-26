package com.gar.resource.exceptions;

import lombok.Getter;

import com.gar.resource.enums.GarExceptionCodeEnum;

@Getter
public class GarException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8235252674075026257L;

	private GarExceptionCodeEnum errorCode;

	private String detailMessage;

	
	public GarException(GarExceptionCodeEnum errorCode, String message) {
		super(message);
		this.errorCode=errorCode;
	}

	public GarException(String message) {
		super(message);
	}
}
