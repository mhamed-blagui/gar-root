package com.gar.resource.web.rest;

import static com.gar.resource.constants.GarConstants.ADMIN;
import static com.gar.resource.constants.GarConstants.LOGIN_REGEX;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.gar.resource.domain.GarUser;
import com.gar.resource.dto.GarUserDTO;
import com.gar.resource.repository.GarUserRepository;
import com.gar.resource.service.GarMailService;
import com.gar.resource.service.GarUserService;
import com.gar.resource.utils.HeaderUtil;
import com.gar.resource.utils.PaginationUtil;
import com.gar.resource.web.rest.vm.ManagedUserVM;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing users.
*/
@RestController
@RequestMapping("/api")
public class GarUserResource {

    private final Logger logger = Logger.getLogger(GarUserResource.class);

    private static final String ENTITY_NAME = "userManagement";

    @Autowired
    private GarUserRepository userRepository;

    @Autowired
    private GarMailService mailService;

    @Autowired
    private GarUserService userService;

    /**
     * POST  /users  : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends an
     * mail with an activation link.
     * The user needs to be activated on creation.
     *
     * @param managedUserVM the user to create
     * @return the ResponseEntity with status 201 (Created) and with body the new user, or with status 400 (Bad Request) if the login or email is already in use
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/users")
    @Timed
    @Secured(ADMIN)
    public ResponseEntity createUser(@Valid @RequestBody ManagedUserVM managedUserVM) throws URISyntaxException {
        logger.debug("REST request to save User : {}"+ managedUserVM);

        if (managedUserVM.getId() != null) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new user cannot already have an ID"))
                .body(null);
        // Lowercase the user login before comparing with database
        } else if (userRepository.findOneByLogin(managedUserVM.getLogin().toLowerCase()).isPresent()) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "userexists", "Login already in use"))
                .body(null);
        } else if (userRepository.findOneByEmail(managedUserVM.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "emailexists", "Email already in use"))
                .body(null);
        } else {
            GarUser newUser = userService.createUser(managedUserVM);
            mailService.sendCreationEmail(newUser);
            return ResponseEntity.created(new URI("/api/users/" + newUser.getLogin()))
                .headers(HeaderUtil.createAlert( "userManagement.created", newUser.getLogin()))
                .body(newUser);
        }
    }

    /**
     * PUT  /users : Updates an existing User.
     *
     * @param managedUserVM the user to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated user,
     * or with status 400 (Bad Request) if the login or email is already in use,
     * or with status 500 (Internal Server Error) if the user couldn't be updated
     */
	@PutMapping("/users")
	@Timed
	@Secured(ADMIN)
	public ResponseEntity<GarUserDTO> updateUser(@Valid @RequestBody ManagedUserVM managedUserVM) {
		logger.debug("REST request to update User : {}" + managedUserVM);
		Optional<GarUser> existingUser = userRepository.findOneByEmail(managedUserVM.getEmail());
		if (existingUser.isPresent() && (!existingUser.get().getId().equals(managedUserVM.getId()))) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "emailexists", "Email already in use")).body(null);
		}
		existingUser = userRepository.findOneByLogin(managedUserVM.getLogin().toLowerCase());
		if (existingUser.isPresent() && (!existingUser.get().getId().equals(managedUserVM.getId()))) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "userexists", "Login already in use")).body(null);
		}
		Optional<GarUserDTO> updatedUser = userService.updateUser(managedUserVM);

		return ResponseUtil.wrapOrNotFound(updatedUser, HeaderUtil.createAlert("userManagement.updated", managedUserVM.getLogin()));
	}

    /**
     * GET  /users : get all users.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and with body all users
     */
    @GetMapping("/users")
    @Timed
    public ResponseEntity<List<GarUserDTO>> getAllUsers(@ApiParam Pageable pageable) {
        final Page<GarUserDTO> page = userService.getAllManagedUsers(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * @return a string list of the all of the roles
     */
    @GetMapping("/users/authorities")
    @Timed
    @Secured(ADMIN)
    public List<String> getAuthorities() {
        return userService.getAuthorities();
    }

    /**
     * GET  /users/:login : get the "login" user.
     *
     * @param login the login of the user to find
     * @return the ResponseEntity with status 200 (OK) and with body the "login" user, or with status 404 (Not Found)
     */
    @GetMapping("/users/{login:" + LOGIN_REGEX + "}")
    @Timed
    public ResponseEntity<GarUserDTO> getUser(@PathVariable String login) {
        logger.debug("REST request to get User : {}"+ login);
        return ResponseUtil.wrapOrNotFound(
            userService.getUserWithAuthoritiesByLogin(login)
                .map(GarUserDTO::new));
    }

    /**
     * DELETE /users/:login : delete the "login" User.
     *
     * @param login the login of the user to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/users/{login:" + LOGIN_REGEX + "}")
    @Timed
    @Secured(ADMIN)
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        logger.debug("REST request to delete User: {}"+ login);
        userService.deleteUser(login);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert( "userManagement.deleted", login)).build();
    }
}
