package com.acm.bootstrap;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by lion on 16/7/24.
 */
public class BootstrapNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        this.registerBeanDefinitionParser("enable-bootstrap",new BootstrapBeanDefinitionParser());
    }
}
