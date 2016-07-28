package com.acm.bootstrap;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesPropertySourceLoader implements PropertySourceLoader {

    public String[] getFileExtensions() {
        return new String[] { "properties", "xml" };
    }

    public PropertySource<?> load(String name, Resource resource) throws IOException {
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        if (!properties.isEmpty()) {
            return new PropertiesPropertySource(name, properties);
        }
        return null;
    }

}