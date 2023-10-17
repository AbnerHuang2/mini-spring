package org.skitii.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.skitii.factory.PropertyValue;
import org.skitii.factory.config.BeanDefinition;
import org.skitii.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author skitii
 * @since 2023/10/13
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    private  InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        return doCreateBean(beanName, beanDefinition);
    }

    public Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        try {
            Object bean = createBeanInstance(beanDefinition);
            // 添加到单例池
            registerSingleton(beanName, bean);

            // 属性注入
            applyPropertyValues(beanName, beanDefinition, bean);

            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void applyPropertyValues(String beanName, BeanDefinition beanDefinition, Object bean) {
        if (beanDefinition.getPropertyValues() == null) {
            return;
        }
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference){
                BeanReference beanReference = (BeanReference) value;
                // 递归获取或创建bean【可能存在循环依赖的问题，后续处理】
                value = getBean(beanReference.getBeanName());
            }
            //设置属性
            BeanUtil.setFieldValue(bean, name, value);

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
