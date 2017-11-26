package com.gar.resource.enums;

import lombok.Getter;

@Getter
public enum GarProfilEnum {

	ADMIN("ADMIN"),COLLABORATOR("COLLABORATOR"),RH("RH");
	
	private String profilName;
	
	GarProfilEnum(String profilName){
		this.profilName = profilName;
	}
}
