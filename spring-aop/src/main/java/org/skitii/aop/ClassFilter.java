package org.skitii.aop;

/**
 * @author skitii
 * @since 2023/10/26
 **/
public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
