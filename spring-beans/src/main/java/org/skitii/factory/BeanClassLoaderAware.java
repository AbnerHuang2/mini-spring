package org.skitii.factory;

/**
 * @author skitii
 * @since 2023/10/24
 **/
public interface BeanClassLoaderAware extends Aware{
    void setBeanClassLoader(ClassLoader classLoader);
}
