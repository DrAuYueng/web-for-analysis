package com.acm.bootstrap.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import service.DisconfConfig;

import com.acm.bootstrap.EnvironmentInitializer;
import com.acm.bootstrap.PropertySourceLocator;
import com.acm.disconf.CloudDisconfPropertySourceLocator;
import com.acm.disconf.mgr.CloudConfigMgr;
import com.acm.disconf.mgr.DisconfigMgr;
import com.baidu.disconf.client.store.aspect.DisconfAspectJ;

@Configuration
public class ConfigBootstrapConfiguration implements EnvironmentInitializer {

    @Autowired
    private List<PropertySourceLocator> locators;

    @ConditionalOnProperty(value = "config.disconf.enable", matchIfMissing = false)
    @EnableConfigurationProperties(DisconfConfig.class)
    protected static class DisconfMgrConfiguration {

        @Bean
        public CloudConfigMgr disconfigMgr() {
            return new DisconfigMgr();
        }

        @Bean
        public CloudDisconfPropertySourceLocator cloudDisconfPropertySourceLocator(
                ConfigurableApplicationContext applicationContext, DisconfConfig disconfConfig) {
            return new CloudDisconfPropertySourceLocator(applicationContext, disconfConfig);
        }

        @Bean
        public DisconfAspectJ disconfAspectJ() {
            return new DisconfAspectJ();
        }
    }

    // @Bean
    // public DisconfMgrBean disconfMgrBean(DisconfConfig disconfConfig) {
    // DisconfMgrBean mgrBean = new DisconfMgrBean();
    // mgrBean.setScanPackage("foo");
    // return mgrBean;
    // }
    //
    // @Bean
    // public DisconfMgrBeanSecond disconfMgrBeanSecond() {
    // return new DisconfMgrBeanSecond();
    // }
    //
    // @Bean
    // public ReloadablePropertiesFactoryBean reloadablePropertiesFactoryBean()
    // {
    // ReloadablePropertiesFactoryBean factoryBean = new
    // ReloadablePropertiesFactoryBean();
    // factoryBean.setLocations(Arrays.asList(new String[] { "redis.properties"
    // }));
    // return factoryBean;
    // }

    public void initializeEnvironment(Environment env) {
        for (PropertySourceLocator locator : locators) {
            locator.locate(env);
        }

    }

}