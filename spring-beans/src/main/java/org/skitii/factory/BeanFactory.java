package org.skitii.factory;

/**
 * bean工厂接口
 * @author skitii
 * @since 2023/10/10
 **/
public interface BeanFactory {

    Object getBean(String name);

    Object getBean(String name, Object... args);
}
