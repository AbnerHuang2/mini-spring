package org.skitii.factory.support;

import org.skitii.factory.config.BeanDefinition;

/**
 * @author skitii
 * @since 2023/10/13
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        try {
            Object bean = beanDefinition.getBeanClass().newInstance();
            getFactoryBeanObjectCache().put(beanName, bean);
            return bean;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
