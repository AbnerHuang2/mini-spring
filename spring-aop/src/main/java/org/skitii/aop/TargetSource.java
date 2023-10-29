package org.skitii.aop;

import org.skitii.utils.ClassUtils;

/**
 * @author skitii
 * @since 2023/10/26
 **/
public class TargetSource {
    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    /**
     * 这里很关键，决定了代理类是否继承目标类的接口，或者和目标类的关联关系
     * @return
     */
    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }
}
