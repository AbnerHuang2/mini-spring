package org.skitii.factory;

import java.util.Map;

/**
 * @author skitii
 * @since 2023/10/23
 **/
public interface ListableBeanFactory extends BeanFactory {

    String[] getBeanDefinitionNames();

    <T> Map<String, T> getBeansOfType(Class<T> type);
}
