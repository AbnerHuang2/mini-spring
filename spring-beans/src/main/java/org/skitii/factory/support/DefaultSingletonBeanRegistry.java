package org.skitii.factory.support;

import org.skitii.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author skitii
 * @since 2023/10/16
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    // 用来保存所有的bean信息
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>(16);

    public Map<String, Object> getFactoryBeanObjectCache() {
        return factoryBeanObjectCache;
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        factoryBeanObjectCache.put(beanName, singletonObject);
    }

    @Override
    public Object getSingleton(String beanName) {
        return factoryBeanObjectCache.get(beanName);
    }
}
