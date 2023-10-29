package org.skitii.factory.config;

import org.skitii.BeansException;
import org.skitii.factory.PropertyValues;

/**
* Subinterface of {@link BeanPostProcessor} that adds a before-instantiation callback,
* and a callback after instantiation but before explicit properties are set or
* autowiring occurs.
*
* <p>Typically used to suppress default instantiation for specific target beans,
* for example to create proxies with special TargetSources (pooling targets,
* lazily initializing targets, etc), or to implement additional injection strategies
* such as field injection.
*
* <p><b>NOTE:</b> This interface is a special purpose interface, mainly for
* internal use within the framework. It is recommended to implement the plain
* {@link BeanPostProcessor} interface as far as possible.
 *
 * @author skitii
 * @since 2023/10/26
 **/
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{
    /**
     * Apply this BeanPostProcessor <i>before the target bean gets instantiated</i>.
     * The returned bean object may be a proxy to use instead of the target bean,
     * effectively suppressing default instantiation of the target bean.
     *
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param beanClass
     * @param beanName
     * @return
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName);

    /**
     * Post-process the given property values before the factory applies them
     * to the given bean. Allows for checking whether all dependencies have been
     * satisfied, for example based on a "Required" annotation on bean property setters.
     *
     * 在 Bean 对象实例化完成后，设置属性操作之前执行此方法
     *
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;

}
