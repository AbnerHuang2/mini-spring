package org.skitii.factory;

/**
 * @author skitii
 * @since 2023/10/10
 **/
public interface BeanFactory {

    Object getBean(String name);
}