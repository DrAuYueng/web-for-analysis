package com.acm.bootstrap.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import service.MessageQueueConfigService;

import com.acm.bootstrap.EnvironmentInitializer;

@Configuration
public class DemoBootstrapConfiguration implements EnvironmentInitializer {

    @Bean
    public MessageQueueConfigService messageQueueConfigService() {
        return new MessageQueueConfigService();
    }

    public void initializeEnvironment(Environment env) {
        if (env instanceof ConfigurableEnvironment) {
            MutablePropertySources mps = ((ConfigurableEnvironment) env).getPropertySources();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("config.port",8080);
            PropertySource<Map<String, Object>> ps = new PropertySource<Map<String, Object>>("demoMap", map) {

                @Override
                public Object getProperty(String name) {
                    return source.get(name);
                }
            };

            mps.addFirst(ps);
        }

    }
}
