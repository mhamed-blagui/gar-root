package com.gar.resource.service.mapper;

import com.gar.resource.domain.GarAuthority;
import com.gar.resource.domain.GarUser;
import com.gar.resource.dto.GarUserDTO;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Mapper for the entity User and its DTO called UserDTO.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */
@Service
public class UserMapper {

    public GarUserDTO userToUserDTO(GarUser user) {
        return new GarUserDTO(user);
    }

    public List<GarUserDTO> usersToUserDTOs(List<GarUser> users) {
        return users.stream()
            .filter(Objects::nonNull)
            .map(this::userToUserDTO)
            .collect(Collectors.toList());
    }

    public GarUser userDTOToUser(GarUserDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else {
            GarUser user = new GarUser();
            user.setId(userDTO.getId());
            user.setLogin(userDTO.getLogin());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setImageUrl(userDTO.getImageUrl());
            user.setActivated(userDTO.isActivated());
            user.setLangKey(userDTO.getLangKey());
            Set<GarAuthority> authorities = this.authoritiesFromStrings(userDTO.getAuthorities());
            if(authorities != null) {
                user.setAuthorities(authorities);
            }
            return user;
        }
    }

    public List<GarUser> userDTOsToUsers(List<GarUserDTO> userDTOs) {
        return userDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::userDTOToUser)
            .collect(Collectors.toList());
    }

    public GarUser userFromId(Long id) {
        if (id == null) {
            return null;
        }
        GarUser user = new GarUser();
        user.setId(id);
        return user;
    }

    public Set<GarAuthority> authoritiesFromStrings(Set<String> strings) {
        return strings.stream().map(string -> {
            GarAuthority auth = new GarAuthority();
            auth.setName(string);
            return auth;
        }).collect(Collectors.toSet());
    }
}
