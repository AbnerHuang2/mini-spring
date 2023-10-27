package org.skitii.aop.springframework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.skitii.aop.MethodAfterAdvice;

/**
 * @author skitii
 * @since 2023/10/26
 **/
public class MethodAfterAdviceInterceptor implements MethodInterceptor {
    private MethodAfterAdvice advice;

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object proceed = mi.proceed();
        advice.after(mi.getMethod(), mi.getArguments(), mi.getThis(), proceed);
        return proceed;
    }
}
