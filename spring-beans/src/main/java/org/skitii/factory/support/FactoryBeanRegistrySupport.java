package org.skitii.factory.support;

import org.skitii.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author skitii
 * @since 2023/10/24
 **/
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<String, Object>();


    protected Object getObjectFromFactoryBean(FactoryBean<?> factoryBean, String name) {
        if (factoryBean.isSingleton()) {
            Object object = this.factoryBeanObjectCache.get(name);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factoryBean, name);
                this.factoryBeanObjectCache.put(name, (object != null ? object : NULL_OBJECT));
            }
            return (object != NULL_OBJECT ? object : null);
        } else {
            return doGetObjectFromFactoryBean(factoryBean, name);
        }
    }

    private Object doGetObjectFromFactoryBean(FactoryBean<?> factoryBean, String name) {
        return factoryBean.getObject();
    }

    protected Object getCachedObjectForFactoryBean(String beanName) {
        Object obj = this.factoryBeanObjectCache.get(beanName);
        return (obj != NULL_OBJECT ? obj : null);
    }

}
