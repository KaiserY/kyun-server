package com.kaisery.fs.security;

import com.kaisery.fs.entity.User;
import com.kaisery.fs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class MongoDBAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        User user = userRepository.findByUserName(username);

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        grantedAuthorityList.addAll(user.getRoles());
        grantedAuthorityList.addAll(user.getPermissions());

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorityList);
    }
}
