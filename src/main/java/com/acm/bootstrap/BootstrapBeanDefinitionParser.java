package com.acm.bootstrap;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
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

        super.doParse(element, parserContext, builder);
    }

}
