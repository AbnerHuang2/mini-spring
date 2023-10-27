package org.skitii.context.test.aop;


import org.skitii.aop.MethodAfterAdvice;

import java.lang.reflect.Method;

public class UserServiceAfterAdvice implements MethodAfterAdvice {

    @Override
    public void after(Method method, Object[] args, Object target, Object res) throws Throwable {
        System.out.println("方法处理完之后到我了：" + method.getName() + "，返回值：" + res);
    }

}
