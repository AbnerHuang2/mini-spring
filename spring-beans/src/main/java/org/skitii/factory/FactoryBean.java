package org.skitii.factory;

/**
 * FactoryBean是一个接口，实现该接口的类，可以看成是一个工厂Bean，即该类的实例，可以看成是一个工厂，该工厂可以生产对象。
 * 使用场景：
 *  1. 处理原型对象
 *  2. 处理接口的实现
 *  3. 处理一些特殊的bean的创建
 *  4. 当成工厂对象使用
 *
 * 实现原理：
 *  1. 加载bean定义阶段。bean定义中存储scope属性，解析xml是注入scope属性到bean定义中
 *  2. 获取bean阶段。
 *      2.1 扩展一个FactoryBeanRegistrySupport类，该类继承了DefaultSingletonBeanRegistry类，用于处理工厂bean的注册和获取
 *      2.2 在AbstractBeanFactory#getBean方法中，判断bean是否是FactoryBean类型，如果是，调用getObject方法获取bean。
 *
 * @author skitii
 * @since 2023/10/24
 **/
public interface FactoryBean<T> {

    T getObject();

    Class<?> getObjectType();

    default boolean isSingleton() {
        return true;
    }
}
