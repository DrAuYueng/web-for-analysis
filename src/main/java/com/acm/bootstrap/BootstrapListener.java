package com.acm.bootstrap;

import org.springframework.core.env.Environment;

public interface BootstrapListener {
    void environmentPrepared(Environment env);
}
