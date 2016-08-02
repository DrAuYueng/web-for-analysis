package com.acm.disconf;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import service.DisconfConfig;

import com.acm.bootstrap.PropertySourceLocator;
import com.baidu.disconf.client.DisconfMgr;

public class CloudDisconfPropertySourceLocator implements PropertySourceLocator {
    private DisconfConfig disconfConfig;
    private ConfigurableApplicationContext applicationContext;

    public CloudDisconfPropertySourceLocator(ConfigurableApplicationContext applicationContext, DisconfConfig disconfConfig) {
        super();
        this.disconfConfig = disconfConfig;
        this.applicationContext = applicationContext;
    }

    public PropertySource<?> locate(Environment environment) {
        DisconfMgr.getInstance().start(Arrays.asList(disconfConfig.getScanPackageList()));
        for (String filename : disconfConfig.getLocations()) {
            DisconfMgr.getInstance().reloadableScan(filename);
            Resource resource = applicationContext.getResource(filename);
            try {
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                if (environment instanceof ConfigurableEnvironment) {
                    MutablePropertySources mps = ((ConfigurableEnvironment) environment).getPropertySources();

                    mps.addLast(new PropertiesPropertySource("disconfig:[" + filename + "]", properties));
                }
            } catch (IOException e) {
                continue;
            }

        }
        return null;
    }
}
