package com.acm.bootstrap.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.acm.bootstrap.processors.CloudConfigBeanPostProcessor;

@Configuration
public class ConfigNormalConfiguration {
    @Bean
    public CloudConfigBeanPostProcessor cloudConfigBeanPostProcessor() {
        return new CloudConfigBeanPostProcessor();
    }
}
