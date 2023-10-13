package org.skitii.factory.config;

/**
 * @author skitii
 * @since 2023/10/13
 * 通过BeanDefinition来描述Bean的配置信息，用来保存创建bean所需要的各种信息
 **/
public class BeanDefinition {
    Class beanClass;
    String beanClassName;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }
}
