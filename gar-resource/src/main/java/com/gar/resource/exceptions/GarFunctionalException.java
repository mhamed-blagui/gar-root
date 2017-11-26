package com.gar.resource.exceptions;

import com.gar.resource.enums.GarExceptionCodeEnum;

public class GarFunctionalException extends GarException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8834533117054124442L;
	
	public GarFunctionalException(GarExceptionCodeEnum errorCode, String message) {
		super(errorCode, message);
	}
	
	public GarFunctionalException(String message) {
		super(message);
	}
}
