package org.skitii.factory.config;

import lombok.Data;
import org.skitii.factory.PropertyValues;

/**
 * @author skitii
 * @since 2023/10/13
 * 通过BeanDefinition来描述Bean的配置信息，用来保存创建bean所需要的各种信息
 **/
@Data
public class BeanDefinition {
    Class beanClass;

    PropertyValues propertyValues;

    String initMethodName;

    String destroyMethodName;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

}
