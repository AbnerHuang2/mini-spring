package org.skitii.context.support;

import org.skitii.core.convert.ConversionService;
import org.skitii.core.convert.converter.Converter;
import org.skitii.core.convert.converter.ConverterFactory;
import org.skitii.core.convert.converter.GenericConverter;
import org.skitii.core.convert.support.GenericConversionService;
import org.skitii.factory.FactoryBean;
import org.skitii.factory.InitializingBean;

import java.util.Set;

/**
 * @author skitii
 * @since 2023/10/30
 **/
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {
    private Set<?> converters;
    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public void afterPropertiesSet() {
        this.conversionService = new GenericConversionService();
        registerConverters(converters, conversionService);
    }

    private void registerConverters(Set<?> converters, GenericConversionService conversionService) {
        if (converters != null) {
            for (Object converter : converters) {
                if (converter instanceof Converter<?, ?>) {
                    conversionService.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    conversionService.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else if (converter instanceof GenericConverter) {
                    conversionService.addConverter((GenericConverter) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object must implement one of the " +
                            "Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }
}
