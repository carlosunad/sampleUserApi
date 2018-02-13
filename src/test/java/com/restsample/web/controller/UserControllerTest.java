package com.restsample.web.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.restsample.Application;
import com.restsample.TestContext;
import com.restsample.data.model.User;
import com.restsample.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class, TestContext.class})
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    @Qualifier(value = "userService")
    private UserService userServiceMock;

    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        Mockito.reset(userServiceMock);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void createUserShouldReturn201() throws Exception{
        // Given that
        User user = createUser();
        user.setUsername("aabbcc");

        Mockito.when(userServiceMock.create(anyObject())).thenReturn(user);

        //when
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"TEST_NAME\"}"))
                .andExpect(status().isCreated());
        // validate
        verify(userServiceMock, times(1)).create(any());

    }

    @Test
    public void getUserShouldReturn200() throws Exception {
        // Given that
        User user = createUser();
        user.setUsername("aabbcc");

        // when
        Mockito.when(userServiceMock.findByUsername(anyObject())).thenReturn(user);

        // validate
        mockMvc.perform(get("/users/sampleName")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        verify(userServiceMock, times(1)).findByUsername("sampleName");
    }

    @Test
    public void updateUserShouldReturn200() throws Exception{
        // Given that
        User user = createUser();
        user.setUsername("aabbcc");

        Mockito.doReturn(user).when(userServiceMock).findByUsername(anyObject());

        // when
        mockMvc.perform(get("/users/sampleName")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isOk());
        // validate
        verify(userServiceMock, times(1)).findByUsername(any());
    }

    @Test
    public void deleteUserShouldReturn200() throws Exception{
        // Given that
        User user = createUser();
        user.setUsername("aabbcc");

        // when
        Mockito.doReturn(user).when(userServiceMock).findByUsername(anyObject());
        Mockito.doNothing().when(userServiceMock).delete(anyObject());

        // validate
        mockMvc.perform(delete("/users/sampleName")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\": \"TEST_NAME\"}"))
            .andExpect(status().isOk());
        verify(userServiceMock, times(1)).delete(any());
    }

    private User createUser(){
        final User user = new User();
        user.setUsername("TEST_NAME");
        return user;
    }

}
