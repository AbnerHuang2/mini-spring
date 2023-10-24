package org.skitii.context.common;


import org.skitii.factory.ConfigurableListableBeanFactory;
import org.skitii.factory.PropertyValue;
import org.skitii.factory.PropertyValues;
import org.skitii.factory.config.BeanDefinition;
import org.skitii.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("testUserService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("name", "name改为：Skitii"));
    }

}
