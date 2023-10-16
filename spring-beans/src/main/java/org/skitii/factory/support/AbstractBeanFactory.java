package org.skitii.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.skitii.factory.config.BeanDefinition;
import org.skitii.factory.config.ConfigurableBeanFactory;

/**
 * @author skitii
 * @since 2023/10/12
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    public Map<String, BeanDefinition> getMergedBeanDefinitions() {
        return mergedBeanDefinitions;
    }

    private final Map<String, BeanDefinition> mergedBeanDefinitions = new ConcurrentHashMap<>(256);

    @Override
    public Object getBean(String name) {
        return doGetBean(name);
    }

    protected Object doGetBean(String name) {
        // 1. 从缓存中获取
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        // 2. 创建bean
        bean = createBean(name, mergedBeanDefinitions.get(name));
        // 3. 添加到缓存
        registerSingleton(name, bean);
        return bean;
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);

}
