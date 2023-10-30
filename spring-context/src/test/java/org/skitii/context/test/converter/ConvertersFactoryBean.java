package org.skitii.context.test.converter;

import org.skitii.factory.FactoryBean;

import java.util.HashSet;
import java.util.Set;

/**
 * @author skitii
 * @since 2023/10/30
 **/
public class ConvertersFactoryBean implements FactoryBean<Set<?>> {
    @Override
    public Set<?> getObject() {
        HashSet<Object> converters = new HashSet<>();
        StringToLocalDateConverter stringToLocalDateConverter = new StringToLocalDateConverter("yyyy-MM-dd");
        converters.add(stringToLocalDateConverter);
        return converters;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
