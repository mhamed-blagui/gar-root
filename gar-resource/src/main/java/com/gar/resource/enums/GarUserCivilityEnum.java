package com.gar.resource.enums;

import lombok.Getter;

@Getter
public enum GarUserCivilityEnum {

	SINGLE("SINGLE"),MARRIED("MARRIED");
	
	private String civility;
	
	GarUserCivilityEnum(String civility){
		this.civility = civility;
	}
	
}
