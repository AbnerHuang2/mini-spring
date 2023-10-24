package org.skitii.context.common;


import org.skitii.context.TestUserService;
import org.skitii.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if ("testUserService".equals(beanName)) {
            TestUserService userService = (TestUserService) bean;
            userService.setAge(18);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

}
