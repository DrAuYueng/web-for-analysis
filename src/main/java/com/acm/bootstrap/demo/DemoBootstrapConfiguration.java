package com.acm.bootstrap.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import service.MessageQueueConfigService;

import com.acm.bootstrap.EnvironmentInitializer;

@Configuration
public class DemoBootstrapConfiguration implements EnvironmentInitializer {

    @Bean
    public MessageQueueConfigService messageQueueConfigService() {
        return new MessageQueueConfigService();
    }

    public void initializeEnvironment(Environment env) {
        String[] profiles = env.getActiveProfiles();
        System.out.println(profiles);
    }
}
