package com.acm.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Created by lion on 16/7/24.
 */
public class BootstrapBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    /** Constant for the id attribute */
    public static final String ID_ATTRIBUTE = "id";

    /** Constant for the name attribute */
    public static final String NAME_ATTRIBUTE = "name";

    public static final String ID_DEFAULT = BootstrapConfigurer.class + ".id";

    public static final String NAME_DEFAULT = BootstrapConfigurer.class + ".name";

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

        AnnotationConfigApplicationContext bootstrapContext = new AnnotationConfigApplicationContext(findSource());
        List<EnvironmentInitializer> list = getOrderedBeansOfType(bootstrapContext, EnvironmentInitializer.class);
        for (EnvironmentInitializer initializer : list) {
            initializer.initializeEnvironment(parserContext.getDelegate().getReaderContext().getEnvironment());
        }
        super.doParse(element, parserContext, builder);
    }

    private Class<?>[] findSource() {
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
}
