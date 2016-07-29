package com.acm.bootstrap.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import service.DisconfConfig;

import com.baidu.disconf.client.DisconfMgrBean;
import com.baidu.disconf.client.DisconfMgrBeanSecond;
import com.baidu.disconf.client.addons.properties.ReloadablePropertiesFactoryBean;

@Configuration
@EnableConfigurationProperties
@ConditionalOnProperty(value = "config.disconf.enable", matchIfMissing = false)
public class DisconfBootstrapConfiguration {
    @Bean
    public DisconfConfig disconfConfig() {
        return new DisconfConfig();
    }

    @Bean
    public DisconfMgrBean disconfMgrBean() {
        return new DisconfMgrBean();
    }

    @Bean
    public DisconfMgrBeanSecond disconfMgrBeanSecond() {
        return new DisconfMgrBeanSecond();
    }

    @Bean
    public ReloadablePropertiesFactoryBean reloadablePropertiesFactoryBean() {
        ReloadablePropertiesFactoryBean factoryBean = reloadablePropertiesFactoryBean();
        factoryBean.setLocations(disconfConfig().getLocations());
        return factoryBean;
    }
}
