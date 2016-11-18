package com.kaisery.fs.service;


import com.kaisery.fs.entity.OAuth2Client;
import com.kaisery.fs.repository.OAuth2ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class MongoClientDetailsService extends InMemoryClientDetailsService {

    @Autowired
    private OAuth2ClientRepository oAuth2ClientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        return oAuth2ClientRepository.findByClientId(clientId).orElseThrow(() -> new ClientRegistrationException(String.format("Client [%s]  was not found", clientId)));
    }
}
