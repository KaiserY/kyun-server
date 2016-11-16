package com.kaisery.fs.repository;

import com.kaisery.fs.entity.OAuth2Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OAuth2ClientRepository extends MongoRepository<OAuth2Client, String> {

    public Optional<OAuth2Client> findByClientId(String clientId);
}
