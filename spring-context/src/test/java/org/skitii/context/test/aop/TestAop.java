package org.skitii.context.test.aop;

import org.junit.Test;
import org.skitii.context.support.ClassPathXmlApplicationContext;

/**
 * @author skitii
 * @since 2023/10/26
 **/
public class TestAop {
    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-aop.xml");

        IUserService userService = (IUserService) applicationContext.getBean("userService");
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
