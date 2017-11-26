package com.gar.resource.service;

import java.util.List;
import java.util.Set;

import com.gar.resource.domain.GarAuthority;
import com.gar.resource.domain.GarUser;
import com.gar.resource.dto.GarUserDTO;

public interface GarUserMapperService {

	GarUserDTO userToUserDTO(GarUser user);

	List<GarUserDTO> usersToUserDTOs(List<GarUser> users);

	GarUser userDTOToUser(GarUserDTO userDTO);

	List<GarUser> userDTOsToUsers(List<GarUserDTO> userDTOs);

	GarUser userFromId(Long id);

	Set<GarAuthority> authoritiesFromStrings(Set<String> strings);

}
