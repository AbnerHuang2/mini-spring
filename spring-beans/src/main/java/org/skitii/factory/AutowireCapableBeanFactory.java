package org.skitii.factory;

/**
 * 自动装配bean工厂接口， 增加了自动装配bean的能力。主要跟bean实例相关
 * 包括：应用bean后置处理器，初始化前后处理器
 * @author skitii
 * @since 2023/10/23
 **/
public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName);

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName);
}
