package com.pyco.pycozza;

import com.pyco.pycozza.config.WebConfig;
import com.pyco.pycozza.security.ApiSecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ContextConfiguration(classes = {ApiSecurityConfig.class , WebConfig.class})
class PycozzaApplicationTests {

    @Test
    void contextLoads() {

    }

}
