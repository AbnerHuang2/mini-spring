package org.skitii.factory;

import org.skitii.factory.config.BeanDefinition;
import org.skitii.factory.config.ConfigurableBeanFactory;

/**
 * 可配置的可列举的bean工厂接口
 * @author skitii
 * @since 2023/10/23
 **/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName);
    void preInstantiateSingletons();
}
