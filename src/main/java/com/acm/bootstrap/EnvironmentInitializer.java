package com.acm.bootstrap;

import org.springframework.core.env.Environment;

public interface EnvironmentInitializer {
    void initializeEnvironment(Environment env);
}
