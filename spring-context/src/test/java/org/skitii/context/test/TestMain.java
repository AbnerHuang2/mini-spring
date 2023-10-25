package org.skitii.context.test;

import org.skitii.context.test.event.CustomEvent;
import org.skitii.context.test.factorybean.IUserDao;
import org.skitii.context.support.ClassPathXmlApplicationContext;

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

        // test factory bean
        IUserDao userDao = (IUserDao) applicationContext.getBean("proxyUserDao");
        System.out.println(userDao.queryUserName("1"));

        TestPrototypeBeanMapper prototype1 = (TestPrototypeBeanMapper) applicationContext.getBean("testPrototypeBeanMapper");
        TestPrototypeBeanMapper prototype2 = (TestPrototypeBeanMapper) applicationContext.getBean("testPrototypeBeanMapper");
        System.out.printf("prototype1:%s prototype2:%s%n", prototype1, prototype2);

        // test event
        applicationContext.publishEvent(new CustomEvent(applicationContext,  "我发布了一个custom event."));

    }
}
