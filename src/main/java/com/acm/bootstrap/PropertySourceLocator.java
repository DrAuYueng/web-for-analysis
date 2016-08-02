package com.acm.bootstrap;

import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

public interface PropertySourceLocator {

    /**
     * @param environment the current Environment
     * @return a PropertySource or null if there is none
     * 
     * @throws IllegalStateException if there is a fail fast condition
     */
    PropertySource<?> locate(Environment environment);

}