package org.skitii.factory.support;

import com.sun.istack.internal.Nullable;
import org.skitii.factory.BeanFactory;
import org.skitii.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author skitii
 * @since 2023/10/16
 **/
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition bd, Constructor<?> ctor, Object... args) throws InvocationTargetException, InstantiationException, IllegalAccessException;
}
