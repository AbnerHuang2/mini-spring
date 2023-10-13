package org.skitii;

import org.skitii.factory.support.AbstractAutowireCapableBeanFactory;
import org.skitii.factory.config.BeanDefinition;
import org.skitii.factory.support.DefaultListableBeanFactory;

/**
 * @author skitii
 * @since 2023/10/13
 **/
public class TestMain {
    public static void main(String[] args) {
        // 初始化 ioc容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 注册bean
        beanFactory.registerBeanDefinition("helloService", new BeanDefinition(TestUserService.class));
        // 获取bean
        TestUserService helloService = (TestUserService) beanFactory.getBean("helloService");
        helloService.hello();

    }
}
