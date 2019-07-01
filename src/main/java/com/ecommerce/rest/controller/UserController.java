package com.ecommerce.rest.controller;

import com.ecommerce.rest.entity.User;
import com.ecommerce.rest.error.exception.GenericException;
import com.ecommerce.rest.error.exception.NotFoundException;
import com.ecommerce.rest.model.user.request.CreateUserRequest;
import com.ecommerce.rest.model.user.request.LoginRequest;
import com.ecommerce.rest.model.user.request.UpdateUserPasswordRequest;
import com.ecommerce.rest.model.user.response.CreateUserResponse;
import com.ecommerce.rest.model.user.response.GetUserByIdResponse;
import com.ecommerce.rest.model.user.response.LoginResponse;
import com.ecommerce.rest.model.user.response.UpdateUserPasswordResponse;
import com.ecommerce.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/user/{userId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetUserByIdResponse getUser(@PathVariable UUID userId) throws NotFoundException {
        return userService.getUser(userId);
    }

    @RequestMapping(value = "/api/users/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@RequestBody CreateUserRequest userRequest) throws GenericException {
        return userService.createUser(userRequest);
    }

    @RequestMapping(value = "/api/users/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws GenericException {
        return userService.doLogin(loginRequest);
    }

    @RequestMapping(value = "/auth/secured", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateUserRequest getUserDetails(@RequestHeader("Authorization") String authorization) throws GenericException {
        return userService.getUserDetails(authorization);
    }

    @RequestMapping(value = "/api/user/updatePassword", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UpdateUserPasswordResponse updatePassword(@RequestBody UpdateUserPasswordRequest request, @RequestHeader("Authorization") String authorization) throws GenericException, Exception {
        return userService.updatePassword(request, authorization);
    }

    @RequestMapping(value = "/api/user/get_user", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@RequestHeader("Authorization") String authorization) throws GenericException {
        return userService.getValidUser(authorization);
    }
}
