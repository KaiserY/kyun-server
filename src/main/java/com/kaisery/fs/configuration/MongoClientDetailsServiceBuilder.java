package com.kaisery.fs.configuration;

import com.kaisery.fs.entity.Authority;
import com.kaisery.fs.entity.OAuth2Client;
import com.kaisery.fs.repository.OAuth2ClientRepository;
import com.kaisery.fs.service.MongoClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;

public class MongoClientDetailsServiceBuilder extends InMemoryClientDetailsServiceBuilder {

    @Autowired
    private OAuth2ClientRepository oAuth2ClientRepository;

    @Override
    protected void addClient(String clientId, ClientDetails value) {

        OAuth2Client oAuth2Client = new OAuth2Client();

        oAuth2Client.setClientId(value.getClientId());
        oAuth2Client.setClientSecret(value.getClientSecret());
        oAuth2Client.setRefreshTokenValiditySeconds(value.getRefreshTokenValiditySeconds());
        oAuth2Client.setAccessTokenValiditySeconds(value.getAccessTokenValiditySeconds());
        oAuth2Client.setAdditionalInformation(value.getAdditionalInformation());
        oAuth2Client.setAuthorities(value.getAuthorities());
        oAuth2Client.setAuthorizedGrantTypes(value.getAuthorizedGrantTypes());
        oAuth2Client.setRegisteredRedirectUris(value.getRegisteredRedirectUri());
        oAuth2Client.setScope(value.getScope());
        oAuth2Client.setResourceIds(value.getResourceIds());

        oAuth2ClientRepository.save(oAuth2Client);
    }

    @Override
    protected ClientDetailsService performBuild() {
        return new MongoClientDetailsService();
    }
}
