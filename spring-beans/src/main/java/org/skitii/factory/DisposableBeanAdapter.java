package org.skitii.factory;

import cn.hutool.core.util.StrUtil;
import org.skitii.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author skitii
 * @since 2023/10/23
 **/
public class DisposableBeanAdapter implements DisposableBean{
    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() {
        // 调用bean的destroy方法
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        // 配置文件中配置的destroy方法，但是要排除实现了DisposableBean接口的类，因为已经调用过了
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            try {
                Method method = bean.getClass().getMethod(destroyMethodName);
                if (method == null) {
                    throw new RuntimeException("destroy method not found");
                }
                method.invoke(bean);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
