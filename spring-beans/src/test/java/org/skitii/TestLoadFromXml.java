package org.skitii;

import org.skitii.factory.support.BeanDefinitionReader;
import org.skitii.factory.support.DefaultListableBeanFactory;
import org.skitii.factory.support.XmlBeanDefinitionReader;

/**
 * @author skitii
 * @since 2023/10/17
 **/
public class TestLoadFromXml {
    public static void main(String[] args) {
        // 1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.读取配置文件，生成BeanDefinition
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 4.获取Bean
        TestUserService userService = (TestUserService) beanFactory.getBean("testUserService");

        // 5.调用方法
        userService.hello();
    }
}
