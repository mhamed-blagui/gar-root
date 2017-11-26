package com.gar.resource.enums;

import lombok.Getter;

@Getter
public enum GarPropertyEnum {

	DISPLAY_NAME("gargantua.display.name"),
	USER_EXPIRING_PERIOD("gargantua.user.expiring.period");
	
	private String property;
	
	GarPropertyEnum(String property){
		this.property = property;
	}
}
