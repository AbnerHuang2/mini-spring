package org.skitii.factory.support;

import org.skitii.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Java实现构造函数实例化策略
 * @author skitii
 * @since 2023/10/16
 **/
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition bd, Constructor<?> ctor, Object... args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        //如果方法有overide注解，可以通过java实例化。如果没有就只能用cglib去实例化了。
        //return ctor.newInstance(args);

        //通过cglib实例化
        return instantiateWithMethodInjection(bd, ctor, args);
    }

    protected Object instantiateWithMethodInjection(BeanDefinition bd, Constructor<?> ctor, Object... args){
        throw new UnsupportedOperationException("Method Injection not supported in SimpleInstantiationStrategy");
    }

}
