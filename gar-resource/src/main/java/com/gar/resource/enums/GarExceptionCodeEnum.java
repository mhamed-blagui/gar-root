package com.gar.resource.enums;

import lombok.Getter;

@Getter
public enum GarExceptionCodeEnum {

	DATA_BASE_EXCEPTION("gargantua.error.message.database"), FILE_EXCEPTION("gargantua.error.message.file"),
	EMAIL_ADDRESS_WRONG_FORMAT("gargantua.error.message.invalid.email");
	
	private String errorCode;
	
	GarExceptionCodeEnum(String errorCode){
		this.errorCode = errorCode;
	}
}
