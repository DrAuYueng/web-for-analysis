package com.acm.bootstrap;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

public class BootstrapConfigFileListener implements BootstrapListener {

    public void environmentPrepared(Environment env) {
        if (env instanceof ConfigurableEnvironment) {
            PropertySourcesLoader propertySourcesLoader = new PropertySourcesLoader((ConfigurableEnvironment) env, null);
            propertySourcesLoader.load();
        }
    }

}
