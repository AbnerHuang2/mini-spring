package org.skitii.core.convert;

/**
 * A service interface for type conversion. This is the entry point into the convert system.
 * Call {@link #convert(Object, Class)} to perform a thread-safe type conversion using this system.
 *
 * 类型转换抽象接口
 * @author skitii
 * @since 2023/10/30
 **/
public interface ConversionService {

    boolean canConvert(Class<?> sourceType, Class<?> targetType);

    <T> T convert(Object source, Class<T> targetType);

}
