package org.skitii.core.convert.converter;

/**
 * @author skitii
 * @since 2023/10/30
 **/
public interface ConverterFactory<S, R> {

    <T extends R> Converter<S, T> getConverter(Class<T> targetType);
}
