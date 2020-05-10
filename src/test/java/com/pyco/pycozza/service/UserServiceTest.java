package com.pyco.pycozza.service;


import com.pyco.pycozza.model.Product;
import com.pyco.pycozza.model.User;
import com.pyco.pycozza.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest
@AutoConfigureDataMongo
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;


    private static final String TEST_USER_EMAIL = "test@gmail.com";

    @BeforeEach
    public void init() {
        //String fullName, String phone,
        //                String address, String email,
        //                String password, Collection<? extends GrantedAuthority> authorities
        mongoTemplate.remove(User.class).all();
        User testUser = new User("test user", "12345678",
                "test add", TEST_USER_EMAIL, "testPassword");
        mongoTemplate.save(testUser);
        //int id, String name, int categoryId, String imgLink, int price
        User testUser2 = new User("test user 2", "23456789",
                "test add 2", "test2@gmail.com", "test2Password");
        mongoTemplate.save(testUser2);
    }


    @Test
    public void getAllUserReturnTest() {
        List<User> userList = userService.getAllUser();
        Assert.assertEquals(2, userList.size());
    }

    @Test
    public void createUserWithNoDuplicateEmailTest() {
        User user = userService.createUser(new User("test", "12345678", "test Add", TEST_USER_EMAIL, "test Passwrod"));
        Assert.assertNull(user);
    }

    @Test
    public void createUserWithEncryptedPasswordTest() {
        User user = userService.createUser(new User("test", "12345678", "test Add", TEST_USER_EMAIL, "test Passwrod"));
        Assert.assertNotEquals(userService.getUserByEmail(TEST_USER_EMAIL).getPassword(), TEST_USER_EMAIL);
    }

    @Test
    public void getUserByEmailReturnRightUserTest() {
        User user = userService.getUserByEmail(TEST_USER_EMAIL);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getEmail(), TEST_USER_EMAIL);
    }


}
