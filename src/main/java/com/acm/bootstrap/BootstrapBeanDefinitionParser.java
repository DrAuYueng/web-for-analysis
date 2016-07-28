package com.acm.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Created by lion on 16/7/24.
 */
public class BootstrapBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    /**
     * Constant for the id attribute
     */
    public static final String ID_ATTRIBUTE = "id";

    /**
     * Constant for the name attribute
     */
    public static final String NAME_ATTRIBUTE = "name";

    public static final String ID_DEFAULT = BootstrapConfigurer.class + ".id";

    public static final String NAME_DEFAULT = BootstrapConfigurer.class + ".name";

    private ConfigurableEnvironment defaultParentEnvironment;

    @Override
    protected Class<?> getBeanClass(Element element) {
        return BootstrapConfigurer.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {

        String id = element.getAttribute(ID_ATTRIBUTE);
        if (!StringUtils.hasLength(id)) {
            element.setAttribute(ID_ATTRIBUTE, ID_DEFAULT);
        }

        String name = element.getAttribute(NAME_ATTRIBUTE);
        if (!StringUtils.hasLength(name)) {
            element.setAttribute(NAME_ATTRIBUTE, NAME_DEFAULT);
        }

        ConfigurableEnvironment environment = getOrCreateEnvironment();
        // environment prepared,fire the listeners
        for (BootstrapListener listener : getListeners()) {
            listener.environmentPrepared(environment);
        }

        AnnotationConfigApplicationContext bootstrapContext = new AnnotationConfigApplicationContext();
        bootstrapContext.setEnvironment(environment);
        BeanDefinitionLoader loader = new BeanDefinitionLoader(getBeanDefinitionRegistry(bootstrapContext), findSource());
        loader.load();
        bootstrapContext.refresh();
        List<EnvironmentInitializer> list = getOrderedBeansOfType(bootstrapContext, EnvironmentInitializer.class);
        for (EnvironmentInitializer initializer : list) {
            initializer.initializeEnvironment(parserContext.getDelegate().getReaderContext().getEnvironment());
        }
        BeanDefinitionRegistry registry = parserContext.getRegistry();
        // set parent context if necessary
        if (registry instanceof ConfigurableApplicationContext) {

            ConfigurableApplicationContext context = (ConfigurableApplicationContext) registry;
            while (context.getParent() != null) {
                context = (ConfigurableApplicationContext) context.getParent();
            }

            context.setParent(bootstrapContext);

        }
        super.doParse(element, parserContext, builder);
    }

    private ConfigurableEnvironment getOrCreateEnvironment() {
        if (this.defaultParentEnvironment != null) {
            return defaultParentEnvironment;
        }
        return new StandardEnvironment();
    }

    private Object[] findSource() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // Use names and ensure unique to protect against duplicates
        List<String> names = SpringFactoriesLoader.loadFactoryNames(BootstrapConfiguration.class, classLoader);
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
        return sources.toArray(new Class[sources.size()]);
    }

    private <T> List<T> getOrderedBeansOfType(ListableBeanFactory context, Class<T> type) {
        List<T> result = new ArrayList<T>();
        for (String name : context.getBeanNamesForType(type)) {
            result.add(context.getBean(name, type));
        }
        AnnotationAwareOrderComparator.sort(result);
        return result;
    }

    private List<BootstrapListener> getListeners() {
        return SpringFactoriesLoader.loadFactories(BootstrapListener.class, null);
    }

    private BeanDefinitionRegistry getBeanDefinitionRegistry(ApplicationContext context) {
        if (context instanceof BeanDefinitionRegistry) {
            return (BeanDefinitionRegistry) context;
        }
        if (context instanceof AbstractApplicationContext) {
            return (BeanDefinitionRegistry) ((AbstractApplicationContext) context).getBeanFactory();
        }
        throw new IllegalStateException("Could not locate BeanDefinitionRegistry");
    }
}
