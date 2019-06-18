package com.ecommerce.rest.service;

import com.ecommerce.rest.constants.ErrorConstants;
import com.ecommerce.rest.constants.JwtConstants;
import com.ecommerce.rest.dao.UserRepository;
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
import com.ecommerce.rest.util.JwtUtility;
import com.ecommerce.rest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public GetUserByIdResponse getUser(UUID userId) throws NotFoundException {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new NotFoundException();
        }
        return new GetUserByIdResponse(UserMapper.getUserResponseForUser(user));
    }

    public CreateUserResponse createUser(CreateUserRequest userRequest) throws GenericException {
        User user = null;
        try {
            user = userRepository.saveAndFlush(UserMapper.getUserForRequest(userRequest));
        }
        catch (Exception ex) {
            throw new GenericException(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return UserMapper.getUserCreateResponse(user);
    }

    public LoginResponse doLogin(LoginRequest loginRequest) throws GenericException {
        User user = userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (user == null) {
            throw new GenericException(ErrorConstants.INVALID_USER, HttpStatus.NOT_FOUND);
        }
        String basicAuth = JwtUtility.getBasicAuth(loginRequest.getUsername(), loginRequest.getPassword());
        String accessToken = JwtUtility.getAccessToken(basicAuth);
        return UserMapper.getLoginResponse(accessToken);
    }

    public CreateUserRequest getUserDetails(String authorization) throws GenericException {
        List<String> userDetails = getUserDetailsFromAuth(authorization);
        User user = userRepository.findByUsernameAndPassword(userDetails.get(0), userDetails.get(1));
        if (user == null) {
            throw new GenericException(ErrorConstants.TOKEN_EXPIRED, HttpStatus.FORBIDDEN);
        }
        return UserMapper.getUserCreateRequestFromUser(user);
    }

    public UpdateUserPasswordResponse updatePassword(UpdateUserPasswordRequest request, String authorization) throws GenericException, Exception {
        try {
            List<String> userDetails = getUserDetailsFromAuth(authorization);
            String oldPassword = userDetails.get(1);
            if (!oldPassword.equals(request.getOldPassword())) {
                throw new GenericException(ErrorConstants.NO_PASSWORD_MATCH, HttpStatus.BAD_REQUEST);
            }
            User user = userRepository.findByUsernameAndPassword(userDetails.get(0), userDetails.get(1));
            user.setPassword(request.getNewPassword());
            user = userRepository.saveAndFlush(user);
            if (!user.getPassword().equals(request.getNewPassword())) {
                throw new Exception(ErrorConstants.PASSWORD_UPDATE_FAIL);
            }
            UpdateUserPasswordResponse response = new UpdateUserPasswordResponse();
            return response;
        }
        catch (GenericException ex) {
            throw new GenericException(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    private String checkAuth(String authorization) throws GenericException {
        if (StringUtils.isEmpty(authorization)) {
            throw new GenericException(ErrorConstants.AUTH_MISSING, HttpStatus.FORBIDDEN);
        }
        if (!authorization.startsWith(JwtConstants.AUTH_FORMAT)) {
            throw new GenericException(ErrorConstants.AUTH_INVALID, HttpStatus.FORBIDDEN);
        }
        String authToken = authorization.substring(JwtConstants.AUTH_FORMAT.length());
        String tokenStatus = JwtUtility.parseJwtToken(authToken);
        if (tokenStatus.equals(JwtConstants.TOKEN_INVALID_KEY)) {
            throw new GenericException(ErrorConstants.TOKEN_INVALID, HttpStatus.FORBIDDEN);
        }
        byte[] credDecoded = Base64.getDecoder().decode(tokenStatus);
        return new String(credDecoded, StandardCharsets.UTF_8);
    }

    private List<String> getUserDetailsFromAuth(String authorization) throws GenericException {
        String userInfo = checkAuth(authorization);
        List<String> userDetails = Arrays.asList(userInfo.split(":"));
        return userDetails;
    }

    public User getValidUser(String authorization) throws GenericException {
        List<String> userDetails = getUserDetailsFromAuth(authorization);
        User user = userRepository.findByUsernameAndPassword(userDetails.get(0), userDetails.get(1));
        return user;
    }

    public void updateUser(User user) {
        userRepository.saveAndFlush(user);
    }
}
