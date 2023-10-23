package org.skitii.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.skitii.factory.*;
import org.skitii.factory.config.BeanDefinition;
import org.skitii.factory.config.BeanPostProcessor;
import org.skitii.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 负责bean的实例化相关操作
 * @author skitii
 * @since 2023/10/13
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
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
            // 初始化
            bean = initializeBean(beanName, bean, beanDefinition);

            // 注册实现了 DisposableBean 接口的 Bean 对象
            registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 实现了DisposableBean接口或者配置了destroy-method属性
        if (bean instanceof DisposableBean || StrUtil.isNotBlank(beanDefinition.getDestroyMethodName()) ) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
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

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行BeanPostProcessor的前置处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        // 2. 执行bean的初始化方法
        invokeInitMethods(beanName, wrappedBean, beanDefinition);
        // 3. 执行BeanPostProcessor的后置处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {
        // 处理InitializingBean接口的afterPropertiesSet方法
        if (wrappedBean instanceof InitializingBean) {
            ((InitializingBean) wrappedBean).afterPropertiesSet();
        }
        // 处理init-method属性指定的初始化方法
        String initMethodName = beanDefinition.getInitMethodName();
        if (null != initMethodName) {
            try {
                wrappedBean.getClass().getMethod(initMethodName).invoke(wrappedBean);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }


}
