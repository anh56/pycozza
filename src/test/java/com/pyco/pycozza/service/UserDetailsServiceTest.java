package com.pyco.pycozza.service;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureDataMongo
public class UserDetailsServiceTest {

    @Autowired
    private UserDetailsService userDetailsService;

    private static final String TEST_EMAIl  = "test@gmail.com";

    @Autowired
    private MongoTemplate mongoTemplate;


//    @BeforeEach
//    public void init(){}
//
    @Test
    public void loadUserDetailsTest(){
        UserDetails userDetails = userDetailsService.loadUserByUsername(TEST_EMAIl);

        List<GrantedAuthority> list =  new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));

        Assert.assertEquals(userDetails.getAuthorities().toString(), list.toString());

    }
}
