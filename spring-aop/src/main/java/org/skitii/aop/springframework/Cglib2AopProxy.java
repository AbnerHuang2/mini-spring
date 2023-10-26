package org.skitii.aop.springframework;

import net.sf.cglib.proxy.Enhancer;
import org.skitii.aop.AdvisedSupport;

/**
 * @author skitii
 * @since 2023/10/26
 **/
public class Cglib2AopProxy implements AopProxy {
    private final AdvisedSupport advised;

    public Cglib2AopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTargetClass()[0]);
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        return enhancer.create();
    }

    private static class DynamicAdvisedInterceptor implements net.sf.cglib.proxy.MethodInterceptor {
        private final AdvisedSupport advised;

        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object o, java.lang.reflect.Method method, Object[] objects, net.sf.cglib.proxy.MethodProxy methodProxy) throws Throwable {
            CglibMethodInvocation methodInvocation = new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, objects, methodProxy);
            // 如果匹配上了，就执行拦截器的方法
            if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTargetClass()[0])) {
                return advised.getMethodInterceptor().invoke(methodInvocation);
            }
            return methodInvocation.proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
        private final net.sf.cglib.proxy.MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, java.lang.reflect.Method method, Object[] arguments, net.sf.cglib.proxy.MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return methodProxy.invoke(target, arguments);
        }
    }

}
