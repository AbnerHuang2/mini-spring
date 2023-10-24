package org.skitii.factory.support;

import org.skitii.factory.DisposableBean;
import org.skitii.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author skitii
 * @since 2023/10/16
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    /**
     * Internal marker for a null singleton object:
     * used as marker value for concurrent Maps (which don't support null values).
     */
    protected static final Object NULL_OBJECT = new Object();

    // 用来保存所有的bean信息
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(16);

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    public Map<String, Object> getSingletonObjects() {
        return singletonObjects;
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        for (DisposableBean disposableBean : disposableBeans.values()) {
            disposableBean.destroy();
        }
    }

}
