package com.acm.bootstrap.processors;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;

import com.acm.bootstrap.events.CloudConfigApplicationEvent;

public class CloudConfigBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements SmartApplicationListener {

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName)
            throws BeansException {
        for (Field field : bean.getClass().getDeclaredFields()) {
            AnnotationAttributes ann = AnnotatedElementUtils.getAnnotationAttributes(field, Value.class.getName());
            if (ann != null) {
                // to do something
            }
        }
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }

    public void onApplicationEvent(ApplicationEvent event) {
        // TODO Auto-generated method stub

    }

    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        if (eventType == null) {
            return true;
        }
        return CloudConfigApplicationEvent.class.isAssignableFrom(eventType);
    }

    public boolean supportsSourceType(Class<?> sourceType) {
        return true;
    }

}
