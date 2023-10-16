package org.skitii.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.skitii.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author skitii
 * @since 2023/10/16
 **/
public class CglibSubclassingInstantiationStrategy extends SimpleInstantiationStrategy{

    protected Object instantiateWithMethodInjection(BeanDefinition bd, Constructor<?> ctor, Object... args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bd.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (null == ctor) {
            return enhancer.create();
        }
        return enhancer.create(ctor.getParameterTypes(), args);
    }

}
