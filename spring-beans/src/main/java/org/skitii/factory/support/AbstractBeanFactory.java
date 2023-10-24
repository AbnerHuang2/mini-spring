package org.skitii.factory.support;

import org.skitii.factory.FactoryBean;
import org.skitii.factory.config.BeanDefinition;
import org.skitii.factory.config.BeanPostProcessor;
import org.skitii.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author skitii
 * @since 2023/10/12
 **/
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

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
            // 如果是 FactoryBean，则需要调用 FactoryBean#getObject
            bean = getObjectForBeanInstance(bean, name);
            return bean;
        }
        // 2. 创建bean并放入缓存
        bean = createBean(name, mergedBeanDefinitions.get(name));

        return getObjectForBeanInstance(bean, name);
    }

    private Object getObjectForBeanInstance(Object bean, String name) {
        if (!(bean instanceof FactoryBean)) {
            return bean;
        }
        Object object = getCachedObjectForFactoryBean(name);
        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) bean;
            object = getObjectFromFactoryBean(factoryBean, name);
        }
        return object;
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}
