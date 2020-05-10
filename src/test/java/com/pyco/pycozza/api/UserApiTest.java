package com.pyco.pycozza.api;


import com.pyco.pycozza.api.model.CheckUserRequest;
import com.pyco.pycozza.api.model.CreateUserRequest;
import com.pyco.pycozza.controller.UserController;
import com.pyco.pycozza.model.QUser;
import com.pyco.pycozza.model.User;
import com.pyco.pycozza.repository.UserRepository;
import com.pyco.pycozza.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureDataMongo
public class UserApiTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String TEST_USER_EMAIL = "test@gmail.com";

    @BeforeEach
    public void init(){
        mongoTemplate.remove(User.class).all();
        User testUser = new User("test user", "12345678",
                "test add", TEST_USER_EMAIL, passwordEncoder.encode("testPassword"));
        mongoTemplate.save(testUser);

    }


    @Test
    public void loginUserWithoutTokenTest() {

        User user = userService.getUserByEmail(TEST_USER_EMAIL);

        userController.addUser(modelMapper.map(user, CreateUserRequest.class));

        CheckUserRequest checkUserRequest = new CheckUserRequest();

        checkUserRequest.setEmail(TEST_USER_EMAIL);
        checkUserRequest.setPassword("testPassword");

        Assert.assertEquals(HttpStatus.OK, userController.checkUser(checkUserRequest).getStatusCode());

        checkUserRequest.setPassword("test2Password");
        Assert.assertEquals(HttpStatus.BAD_REQUEST, userController.checkUser(checkUserRequest).getStatusCode());

        checkUserRequest.setEmail("test2email");
        Assert.assertEquals(HttpStatus.NOT_FOUND, userController.checkUser(checkUserRequest).getStatusCode());
    }

    @Test
    public void loginUserWithTokenTest(){
        User user = userService.getUserByEmail(TEST_USER_EMAIL);

        userController.addUser(modelMapper.map(user, CreateUserRequest.class));

        CheckUserRequest checkUserRequest = new CheckUserRequest();

        checkUserRequest.setEmail(TEST_USER_EMAIL);
        checkUserRequest.setPassword("testPassword");

        Assert.assertEquals(HttpStatus.OK, userController.login(checkUserRequest).getStatusCode());
        Assert.assertNotNull(userController.login(checkUserRequest).getBody());

        checkUserRequest.setPassword("test2Password");
        Assert.assertEquals(HttpStatus.BAD_REQUEST, userController.login(checkUserRequest).getStatusCode());
        Assert.assertEquals("Wrong user name or password", userController.login(checkUserRequest).getBody());

    }

    @Test
    public void userSignupDuplicateEmailTest(){
        User testUser = new User("test user", "12345678",
                "test add", TEST_USER_EMAIL, "testPassword");
        ResponseEntity responseEntity = userController.addUser(modelMapper.map(testUser, CreateUserRequest.class));
        Assert.assertEquals( HttpStatus.CONFLICT, responseEntity.getStatusCode());

    }

    @Test
    public void userSignupSuccessfulTest(){
        User testUser2 = new User("test user", "12345678",
                "test add", "testSucessful@gamia.com", "testPassword");
        ResponseEntity responseEntity = userController.addUser(modelMapper.map(testUser2, CreateUserRequest.class));
        Assert.assertEquals( HttpStatus.CREATED, responseEntity.getStatusCode());
        Assert.assertNotEquals("testPassword", userService.getUserByEmail(TEST_USER_EMAIL).getPassword());
    }




}
