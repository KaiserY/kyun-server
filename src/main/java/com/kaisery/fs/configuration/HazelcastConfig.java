package com.kaisery.fs.configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.web.WebFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import static java.util.Collections.singletonList;

@Configuration
public class HazelcastConfig {

    @Bean
    public Config config() {

        Config config = new Config();

        JoinConfig joinConfig = config.getNetworkConfig().getJoin();

        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getTcpIpConfig().setEnabled(true).setMembers(singletonList("127.0.0.1"));

        return config;
    }

    @Bean
    public WebFilter webFilter(HazelcastInstance hazelcastInstance) {

        Properties properties = new Properties();
        properties.put("instance-name", hazelcastInstance.getName());
        properties.put("sticky-session", "false");

        return new WebFilter(properties);
    }
}
