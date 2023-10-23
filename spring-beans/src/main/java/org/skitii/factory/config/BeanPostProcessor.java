package org.skitii.factory.config;

/**
 * @author skitii
 * @since 2023/10/23
 **/
public interface BeanPostProcessor {

    default Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
