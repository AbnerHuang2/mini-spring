package org.skitii.core.convert.converter;

/**
 * A converter converts a source object of type {@code S} to a target of type {@code T}.
 *
 * 类型转换处理接口
 * @author skitii
 * @since 2023/10/30
 **/
public interface Converter<S, T> {
    T convert(S source);
}
