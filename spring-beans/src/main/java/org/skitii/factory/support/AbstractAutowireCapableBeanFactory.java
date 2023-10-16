package org.skitii.factory.support;

import org.skitii.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author skitii
 * @since 2023/10/13
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    private  InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        try {
            Object bean = createBeanInstance(beanDefinition);
            registerSingleton(beanName, bean);
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建bean实例
     * 难点在于如何获取构造函数的参数
     * @param beanDefinition
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchFieldException
     */
    private Object createBeanInstance(BeanDefinition beanDefinition) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor[] constructors = beanDefinition.getBeanClass().getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            Object[] args = new Object[constructor.getParameterCount()];
            for (int i = 0; i < constructor.getParameterTypes().length; i++) {
                Class parameterType = constructor.getParameterTypes()[i];
                // 无法处理基本类型，包装类型和有参构造函数的情况
                args[i] = parameterType.newInstance();
            }
            return instantiationStrategy.instantiate(beanDefinition, constructor, args);
        }
        return null;
    }

}
