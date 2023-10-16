package org.skitii;

import org.skitii.factory.PropertyValue;
import org.skitii.factory.PropertyValues;
import org.skitii.factory.support.AbstractAutowireCapableBeanFactory;
import org.skitii.factory.config.BeanDefinition;
import org.skitii.factory.support.DefaultListableBeanFactory;

import java.util.Arrays;

/**
 * @author skitii
 * @since 2023/10/13
 **/
public class TestMain {
    public static void main(String[] args) {
        // 初始化 ioc容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 注册bean
        BeanDefinition beanDefinition = new BeanDefinition(TestUserService.class);
        beanDefinition.setPropertyValues(new PropertyValues(Arrays.asList(new PropertyValue("age", 1))));
        beanFactory.registerBeanDefinition("helloService", beanDefinition);
        // 获取bean
        TestUserService helloService = (TestUserService) beanFactory.getBean("helloService");
        // 调用方法
        helloService.hello();
        System.out.println(helloService.getAge());

    }
}
