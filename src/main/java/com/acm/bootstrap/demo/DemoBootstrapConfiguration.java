package com.acm.bootstrap.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import service.MessageQueueConfigService;

@Configuration
public class DemoBootstrapConfiguration {

    @Bean
    public MessageQueueConfigService messageQueueConfigService() {
        return new MessageQueueConfigService();
    }
}
