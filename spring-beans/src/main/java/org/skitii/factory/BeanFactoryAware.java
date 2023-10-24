package org.skitii.factory;

/**
 * @author skitii
 * @since 2023/10/24
 **/
public interface BeanFactoryAware extends Aware{
    void setBeanFactory(BeanFactory beanFactory);
}
