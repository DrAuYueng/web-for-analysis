package com.acm.bootstrap;

import java.io.IOException;

import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

public interface PropertySourceLoader {
    String[] getFileExtensions();

    PropertySource<?> load(String name, Resource resource) throws IOException;
}
