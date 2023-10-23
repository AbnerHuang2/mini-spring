package org.skitii.factory.config;

import org.skitii.factory.ConfigurableListableBeanFactory;

/**
 * @author skitii
 * @since 2023/10/23
 **/
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
