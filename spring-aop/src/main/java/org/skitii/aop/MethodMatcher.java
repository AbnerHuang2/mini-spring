package org.skitii.aop;

import java.lang.reflect.Method;

/**
 * @author skitii
 * @since 2023/10/26
 **/
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);
}
