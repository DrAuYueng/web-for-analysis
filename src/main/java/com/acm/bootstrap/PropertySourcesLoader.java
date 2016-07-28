package com.acm.bootstrap;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.SpringFactoriesLoader;

public class PropertySourcesLoader {

    private List<PropertySourceLoader> loaders;

    private final ConfigurableEnvironment environment;

    private final ResourceLoader resourceLoader;

    private static final String DEFAULT_LOCATION = "classpath:/";

    private static final String DEFAULT_FILE_NAME = "bootstrap";

    public PropertySourcesLoader(ConfigurableEnvironment environment, ResourceLoader resourceLoader) {
        this.environment = environment;
        this.resourceLoader = resourceLoader == null ? new DefaultResourceLoader() : resourceLoader;
        loaders = SpringFactoriesLoader.loadFactories(PropertySourceLoader.class, null);
    }

    public void load() {
        for (String ext : getAllFileExtensions()) {
            String location = DEFAULT_LOCATION + DEFAULT_FILE_NAME + "." + ext;
            doLoad(location);
        }
    }

    private Set<String> getAllFileExtensions() {
        Set<String> fileExtensions = new HashSet<String>();
        for (PropertySourceLoader loader : this.loaders) {
            fileExtensions.addAll(Arrays.asList(loader.getFileExtensions()));
        }
        return fileExtensions;
    }

    private void doLoad(String location) {
        for (PropertySourceLoader loader : loaders) {
            try {
                Resource resource = resourceLoader.getResource(location);
                String name = "bootstrapConfig:" + location;
                PropertySource<?> source = loader.load(name, resource);
                environment.getPropertySources().addLast(source);
            } catch (IOException e) {
                // TODO Auto-generated catch block

            }
        }
    }
}
