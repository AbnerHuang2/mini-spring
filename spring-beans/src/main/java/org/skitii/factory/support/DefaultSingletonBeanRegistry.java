package org.skitii.factory.support;

import org.skitii.BeansException;
import org.skitii.factory.DisposableBean;
import org.skitii.factory.FactoryBean;
import org.skitii.factory.ObjectFactory;
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

    // 用来保存所有的成品bean信息
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(16);
    //二级缓存，用来保存半成品的bean信息
    private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);
    //三级缓存，用来保存bean工厂
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    public Map<String, Object> getSingletonObjects() {
        return singletonObjects;
    }


    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        if (! this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            // 为什么要从二级缓存中移除. 应该不存在bean还没创建，但是二级缓存中已经有了的情况
            this.earlySingletonObjects.remove(beanName);
        }
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if (null == singletonObject){
            // 从earlySingletonObjects中获取
            singletonObject = earlySingletonObjects.get(beanName);
            if (null == singletonObject){
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (null != singletonFactory){
                    singletonObject = singletonFactory.getObject();
                    // 把三级缓存中的代理对象中的真实对象获取出来，放入二级缓存中
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
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
