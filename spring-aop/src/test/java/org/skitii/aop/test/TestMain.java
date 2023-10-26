package org.skitii.aop.test;

import org.junit.Test;
import org.skitii.aop.AdvisedSupport;
import org.skitii.aop.TargetSource;
import org.skitii.aop.aspectj.AspectJExpressionPointcut;
import org.skitii.aop.springframework.Cglib2AopProxy;
import org.skitii.aop.springframework.JdkDynamicAopProxy;
import org.skitii.aop.test.bean.IUserService;
import org.skitii.aop.test.bean.UserService;

/**
 * @author skitii
 * @since 2023/10/26
 **/
public class TestMain {
    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* org.skitii.aop.test.TestMain.test_aop(..))");
        Class<TestMain> mainClass = TestMain.class;
        System.out.println(pointcut.getClassFilter().matches(mainClass));
        System.out.println(pointcut.getMethodMatcher().matches(mainClass.getMethod("test_aop"), mainClass));
    }

    @Test
    public void test_dynamic(){
        IUserService userService = new UserService();
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* org.skitii.aop.test.bean.IUserService.*(..))"));
        advisedSupport.setMethodInterceptor((methodInvocation) -> {
            long start = System.currentTimeMillis();
            Object result = methodInvocation.proceed();
            System.out.println("监控 - Begin By AOP");
            System.out.println("方法名称：" + methodInvocation.getMethod());
            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
            System.out.println("监控 - End\r\n");
            return result;
        });

        //JDK动态代理
        IUserService proxyJdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println(proxyJdk.queryUserInfo());

        //CGLIB动态代理
        IUserService proxyCglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println(proxyCglib.register("skitii"));

    }

}
