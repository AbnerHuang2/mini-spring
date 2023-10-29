package org.skitii.context.test.scan;

import org.junit.Test;
import org.skitii.context.annotation.Component;
import org.skitii.context.support.ClassPathXmlApplicationContext;
import org.skitii.context.test.aop.IUserService;

@Component("testScan")
public class TestScan {

    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");

        TestScan testScan = (TestScan) applicationContext.getBean("testScan");
        testScan.hello();
    }

    @Test
    public void testPropertiesInject(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");

        IUserService userService = (IUserService) applicationContext.getBean("userService");
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

    public void hello() {
        System.out.println("hello");
    }

}
