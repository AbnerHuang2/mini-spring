package org.skitii.factory.config;

import org.skitii.factory.BeanFactory;

/**
 * @author skitii
 * @since 2023/10/13
 **/
public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
