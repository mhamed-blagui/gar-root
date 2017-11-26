package com.gar.resource.enums;

import lombok.Getter;

@Getter
public enum GarOperationEnum {

	ADD_USER("ADD_USER"), UPDATE_USER("UPDATE_USER"), REMOVE_USER("REMOVE_USER"), ADD_PROFIL("ADD_PROFIL"), REMOVE_PROFIL("REMOVE_PROFIL"), UPDATE_PROFIL("UPDATE_PROFIL"), CONNEXION("CONNEXION");

	private String operation;

	GarOperationEnum(String operation) {
		this.operation = operation;
	}

}
