package org.skitii.factory.support;

import org.skitii.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author skitii
 * @since 2023/10/16
 **/
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition bd, Constructor<?> ctor, Object... args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        return ctor.newInstance(args);
    }
}
