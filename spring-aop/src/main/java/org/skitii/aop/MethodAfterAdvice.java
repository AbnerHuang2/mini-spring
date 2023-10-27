package org.skitii.aop;

import java.lang.reflect.Method;

/**
 * @author skitii
 * @since 2023/10/26
 **/
public interface MethodAfterAdvice extends AfterAdvice{
    /**
     * Callback after a given method is invoked.
     *
     * @param method method being invoked
     * @param args   arguments to the method
     * @param target target of the method invocation. May be <code>null</code>.
     * @throws Throwable if this object wishes to abort the call.
     *                   Any exception thrown will be returned to the caller if it's
     *                   allowed by the method signature. Otherwise the exception
     *                   will be wrapped as a runtime exception.
     */
    void after(Method method, Object[] args, Object target, Object res) throws Throwable;
}
