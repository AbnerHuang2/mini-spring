package org.skitii.factory.support;

import org.skitii.BeansException;
import org.skitii.core.convert.ConversionService;
import org.skitii.factory.FactoryBean;
import org.skitii.factory.StringValueResolver;
import org.skitii.factory.config.BeanDefinition;
import org.skitii.factory.config.BeanPostProcessor;
import org.skitii.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skitii
 * @since 2023/10/12
 **/
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    /**
     * String resolvers to apply e.g. to annotation attribute values
     */
    protected final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    private ConversionService conversionService;

    @Override
    public boolean containsBean(String name) {
        return containsBeanDefinition(name);
    }

    protected abstract boolean containsBeanDefinition(String beanName);

    @Override
    public Object getBean(String name) {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, args);
    }

    protected Object doGetBean(String name, Object[] args) {
        // 1. 从缓存中获取
        Object bean = getSingleton(name);
        if (bean != null) {
            // 如果是 FactoryBean，则需要调用 FactoryBean#getObject
            bean = getObjectForBeanInstance(bean, name);
            return bean;
        }
        // 2. 创建bean并放入缓存
        bean = createBean(name, getBeanDefinition(name), args);

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

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }

    @Override
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }


    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}
