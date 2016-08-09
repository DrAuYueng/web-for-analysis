package com.acm.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;

public class BootstrapConfigurer implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // TODO Auto-generated method stub

    }

    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // Use names and ensure unique to protect against duplicates
        List<String> names = SpringFactoriesLoader.loadFactoryNames(NormalConfiguration.class, classLoader);

        List<Class<?>> sources = new ArrayList<Class<?>>();
        for (String name : names) {
            Class<?> cls = ClassUtils.resolveClassName(name, null);
            try {
                cls.getDeclaredAnnotations();
            } catch (Exception e) {
                continue;
            }
            sources.add(cls);
        }

        BeanDefinitionLoader loader = new BeanDefinitionLoader(registry, sources.toArray());
        loader.load();
    }
}
