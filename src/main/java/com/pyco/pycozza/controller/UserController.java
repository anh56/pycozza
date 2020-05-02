package com.pyco.pycozza.controller;

import com.pyco.pycozza.api.UserApi;
import com.pyco.pycozza.api.model.*;
import com.pyco.pycozza.model.User;
import com.pyco.pycozza.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("")
    public Page<User> getUserByPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        return userService.getUserByPage(page, size);
    }

    @Override
    public ResponseEntity<UserResponseModel> checkUser(@Valid CheckUserRequest checkUserRequest) {
        User user = modelMapper.map(checkUserRequest, User.class);
        User userByEmail = userService.getUserByEmail(user.getEmail());
        if (userByEmail != null){
            if(userByEmail.getPassword().equals(user.getPassword())){
                UserResponseModel userResponse = modelMapper.map(userByEmail, UserResponseModel.class);
                return new ResponseEntity<>(userResponse, HttpStatus.FOUND);
            }
            else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> addUser(@Valid CreateUserRequest createUserRequest) {
        User user = modelMapper.map(createUserRequest, User.class);
        User persistUser = userService.createUser(user);
        ObjectCreationSuccessResponse result = new ObjectCreationSuccessResponse();
        result.setId(persistUser.getId().toString());
        result.setStatus(HttpStatus.CREATED.value());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
