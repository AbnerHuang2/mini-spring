package org.skitii.factory.config;

/**
 * @author skitii
 * @since 2023/10/12
 **/
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

    /**
     * 销毁单例对象
     */
    void destroySingletons();
}
