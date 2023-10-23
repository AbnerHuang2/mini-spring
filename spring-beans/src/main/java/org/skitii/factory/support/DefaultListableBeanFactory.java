package org.skitii.factory.support;

import org.skitii.factory.ConfigurableListableBeanFactory;
import org.skitii.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author skitii
 * @since 2023/10/13
 **/
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.getMergedBeanDefinitions().put(beanName,beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.getMergedBeanDefinitions().get(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getMergedBeanDefinitions().keySet().toArray(new String[0]);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        Map<String, T> result = new HashMap<>();
        getMergedBeanDefinitions().forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public void preInstantiateSingletons() {
        this.getMergedBeanDefinitions().keySet().forEach(this::getBean);
    }
}
