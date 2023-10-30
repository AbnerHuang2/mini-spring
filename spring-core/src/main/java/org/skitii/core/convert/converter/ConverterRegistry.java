package org.skitii.core.convert.converter;

/**
 * @author skitii
 * @since 2023/10/30
 **/
public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);

    void addConverter(GenericConverter converter);

    void addConverterFactory(ConverterFactory<?, ?> converterFactory);
}
