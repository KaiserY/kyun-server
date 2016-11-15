package com.kaisery.fs.repository;

import com.kaisery.fs.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    public Optional<User> findByUserName(String userName);
}
