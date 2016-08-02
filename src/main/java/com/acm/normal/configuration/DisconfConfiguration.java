package com.acm.normal.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import service.DisconfConfig;

import com.baidu.disconf.client.DisconfMgrBean;
import com.baidu.disconf.client.DisconfMgrBeanSecond;
import com.baidu.disconf.client.addons.properties.ReloadablePropertiesFactoryBean;

@Configuration
@ConditionalOnProperty(value = "config.disconf.enable", matchIfMissing = false)
@EnableConfigurationProperties
public class DisconfConfiguration {

    @Autowired
    private DisconfConfig disconfConfig;

    @Bean
    public DisconfMgrBean disconfMgrBean(DisconfConfig disconfConfig) {
        DisconfMgrBean mgrBean = new DisconfMgrBean();
        mgrBean.setScanPackage("foo");
        return mgrBean;
    }

    @Bean
    public DisconfMgrBeanSecond disconfMgrBeanSecond() {
        return new DisconfMgrBeanSecond();
    }

    @Bean
    public ReloadablePropertiesFactoryBean reloadablePropertiesFactoryBean() {
        ReloadablePropertiesFactoryBean factoryBean = new ReloadablePropertiesFactoryBean();
        factoryBean.setLocations(Arrays.asList(new String[] { "redis.properties" }));
        return factoryBean;
    }

}