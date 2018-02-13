package com.restsample;

import com.restsample.service.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }
}