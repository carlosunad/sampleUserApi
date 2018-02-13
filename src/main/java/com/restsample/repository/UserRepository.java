package com.restsample.repository;

import com.restsample.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    public List<User> findAll();
    public User findByUsername(String username);
    public void deleteByUsername(String username);
}
