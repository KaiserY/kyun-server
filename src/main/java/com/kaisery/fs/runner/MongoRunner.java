package com.kaisery.fs.runner;

import com.kaisery.fs.entity.Permission;
import com.kaisery.fs.entity.Role;
import com.kaisery.fs.entity.User;
import com.kaisery.fs.repository.ResourceRepository;
import com.kaisery.fs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.util.Collections.singletonList;

@Component
public class MongoRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public void run(String... strings) throws Exception {

        userRepository.deleteAll();

        User admin = new User();
        admin.setUserName("admin");
        admin.setPassword("12345678");
        admin.setRoles(singletonList(Role.ROLE_ADMIN));
        admin.setPermissions(singletonList(Permission.ALL));

        userRepository.save(admin);

        User user = new User();
        user.setUserName("user");
        user.setPassword("12345678");
        user.setRoles(singletonList(Role.ROLE_USER));
        user.setPermissions(singletonList(Permission.ALL));

        userRepository.save(user);
    }
}
