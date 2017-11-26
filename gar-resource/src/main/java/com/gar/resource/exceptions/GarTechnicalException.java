package com.gar.resource.exceptions;

import com.gar.resource.enums.GarExceptionCodeEnum;

public class GarTechnicalException extends GarException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7541853400881747404L;

	public GarTechnicalException(GarExceptionCodeEnum errorCode, String message) {
		super(errorCode, message);
	}

	public GarTechnicalException(String message) {
		super(message);
	}
}
