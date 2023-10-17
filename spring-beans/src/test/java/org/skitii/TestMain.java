package org.skitii;

import org.skitii.factory.PropertyValue;
import org.skitii.factory.PropertyValues;
import org.skitii.factory.config.BeanDefinition;
import org.skitii.factory.config.BeanReference;
import org.skitii.factory.support.DefaultListableBeanFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author skitii
 * @since 2023/10/13
 **/
public class TestMain {
    public static void main(String[] args) {
        // 初始化 ioc容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 注册bean
        beanFactory.registerBeanDefinition("testUserMapper", new BeanDefinition(TestUserMapper.class));

        BeanDefinition beanDefinition = new BeanDefinition(TestUserService.class);
        List<PropertyValue> list = Arrays.asList(new PropertyValue("name", "skitii"),
                new PropertyValue("age", 1),
                new PropertyValue("testUserMapper", new BeanReference("testUserMapper")));
        beanDefinition.setPropertyValues(new PropertyValues(list));
        beanFactory.registerBeanDefinition("helloService", beanDefinition);

        // 获取bean
        TestUserService helloService = (TestUserService) beanFactory.getBean("helloService");
        // 调用方法
        helloService.hello();

    }
}
