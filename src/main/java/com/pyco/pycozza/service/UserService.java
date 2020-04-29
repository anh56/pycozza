package com.pyco.pycozza.service;


import com.pyco.pycozza.model.User;
import com.pyco.pycozza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getUserByPage(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return userRepository.findAll(pageable);
    }


    public List<User> getAllUser() {
        return userRepository.findAll();
    }

//    public void addUser() {
//        User user = new User();
//        return userRepository.save(user);
//    }
}
