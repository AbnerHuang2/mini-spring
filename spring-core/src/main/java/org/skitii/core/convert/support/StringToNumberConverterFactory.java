package org.skitii.core.convert.support;

import org.skitii.core.convert.converter.Converter;
import org.skitii.core.convert.converter.ConverterFactory;
import org.skitii.core.utils.NumberUtils;

/**
 * @author skitii
 * @since 2023/10/30
 **/
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {
    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<T>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {
        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }

}
