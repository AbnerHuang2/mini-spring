package org.skitii.factory.config;

import org.skitii.factory.BeanFactory;

/**
 * 可配置的bean工厂接口。
 * 1. 配置bean定义
 * 2. 配置bean实例
 * @author skitii
 * @since 2023/10/13
 **/
public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
