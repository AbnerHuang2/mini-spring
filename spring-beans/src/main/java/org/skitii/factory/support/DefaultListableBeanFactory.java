package org.skitii.factory.support;

import org.skitii.factory.config.BeanDefinition;

/**
 * @author skitii
 * @since 2023/10/13
 **/
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.getMergedBeanDefinitions().put(beanName,beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.getMergedBeanDefinitions().get(beanName);
    }
}
