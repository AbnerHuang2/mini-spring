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
    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;
    Class beanClass;

    PropertyValues propertyValues;

    String initMethodName;

    String destroyMethodName;

    // 默认单例
    String scope = SCOPE_SINGLETON;

    boolean singleton = true;

    boolean prototype = false;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

}
