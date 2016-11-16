package com.kaisery.fs.service;


import com.kaisery.fs.entity.OAuth2Client;
import com.kaisery.fs.repository.OAuth2ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

@Service
public class MongoClientDetailsService implements ClientDetailsService {

    @Autowired
    private OAuth2ClientRepository oAuth2ClientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OAuth2Client oAuth2Client = oAuth2ClientRepository.findByClientId(clientId).orElseThrow(() -> new ClientRegistrationException(String.format("Client [%s]  was not found", clientId)));

        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId(oAuth2Client.getClientId());
        baseClientDetails.setClientSecret(oAuth2Client.getClientSecret());
        baseClientDetails.setRefreshTokenValiditySeconds(oAuth2Client.getRefreshTokenValiditySeconds());
        baseClientDetails.setAccessTokenValiditySeconds(oAuth2Client.getAccessTokenValiditySeconds());
        baseClientDetails.setAdditionalInformation(oAuth2Client.getAdditionalInformation());
        baseClientDetails.setAuthorities(oAuth2Client.getAuthorities());
        baseClientDetails.setAuthorizedGrantTypes(oAuth2Client.getAuthorizedGrantTypes());
        baseClientDetails.setAutoApproveScopes(oAuth2Client.getAutoApproveScopes());
        baseClientDetails.setRegisteredRedirectUri(oAuth2Client.getRegisteredRedirectUris());
        baseClientDetails.setScope(oAuth2Client.getScope());
        baseClientDetails.setResourceIds(oAuth2Client.getResourceIds());

        return baseClientDetails;
    }
}
