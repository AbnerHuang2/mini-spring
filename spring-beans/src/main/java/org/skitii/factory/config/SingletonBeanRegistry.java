package org.skitii.factory.config;

import com.sun.istack.internal.Nullable;

/**
 * @author skitii
 * @since 2023/10/12
 **/
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);

    @Nullable
    Object getSingleton(String beanName);
}
