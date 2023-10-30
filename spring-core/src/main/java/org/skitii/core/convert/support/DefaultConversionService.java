package org.skitii.core.convert.support;

import org.skitii.core.convert.converter.ConverterRegistry;

/**
 * @author skitii
 * @since 2023/10/30
 **/
public class DefaultConversionService extends GenericConversionService{
    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }
}
