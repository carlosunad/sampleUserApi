package com.restsample.service;

import com.restsample.data.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User create(User user );
    User findOne(String id);
    User findByUsername(String username);
    User update(User user);
    void delete(String username);
    void deleteByUsername(String username);
}
