package com.restsample.service.impl;

import com.restsample.data.model.User;
import com.restsample.repository.UserRepository;
import com.restsample.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Inject
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findOne(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(String id) {
        userRepository.delete(id);
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }
}
