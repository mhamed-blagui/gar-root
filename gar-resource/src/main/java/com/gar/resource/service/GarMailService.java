package com.gar.resource.service;

import com.gar.resource.domain.GarUser;

public interface GarMailService {

	void sendActivationEmail(GarUser user);

	void sendPasswordResetMail(GarUser user);

	void sendCreationEmail(GarUser newUser);

}
