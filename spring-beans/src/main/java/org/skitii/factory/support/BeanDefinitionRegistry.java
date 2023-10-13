package org.skitii.factory.support;

import org.skitii.factory.config.BeanDefinition;

/**
 * @author skitii
 * @since 2023/10/13
 **/
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName);
}
