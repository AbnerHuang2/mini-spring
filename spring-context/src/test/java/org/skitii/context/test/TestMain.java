package org.skitii.context.test;

import org.skitii.context.test.support.ClassPathXmlApplicationContext;

/**
 * @author skitii
 * @since 2023/10/23
 **/
public class TestMain {
    public static void main(String[] args) {
        // 1.初始化BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-processor.xml");
        applicationContext.registerShutdownHook();
        // 4.获取Bean
        TestUserService userService = (TestUserService) applicationContext.getBean("testUserService");

        // 5.调用方法
        userService.hello();
    }
}
