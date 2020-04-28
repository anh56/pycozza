package com.pyco.pycozza.controller;

import com.pyco.pycozza.model.User;
import com.pyco.pycozza.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public Page<User> getUserByPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size){
        return userService.getUserByPage(page,size);
    }


}
