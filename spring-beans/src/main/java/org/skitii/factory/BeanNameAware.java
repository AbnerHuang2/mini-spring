package org.skitii.factory;

/**
 * @author skitii
 * @since 2023/10/24
 **/
public interface BeanNameAware extends Aware {
    void setBeanName(String name);
}
