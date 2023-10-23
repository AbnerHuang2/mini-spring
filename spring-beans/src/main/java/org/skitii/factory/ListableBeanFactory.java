package org.skitii.factory;

import java.util.Map;

/**
 * 可列举的bean工厂接口
 * 扩展了bean工厂接口，用于获取bean定义，bean实例相关接口
 * @author skitii
 * @since 2023/10/23
 **/
public interface ListableBeanFactory extends BeanFactory {

    String[] getBeanDefinitionNames();

    <T> Map<String, T> getBeansOfType(Class<T> type);
}
