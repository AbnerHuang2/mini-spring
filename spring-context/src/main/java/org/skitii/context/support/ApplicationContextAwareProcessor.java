package org.skitii.context.support;

import org.skitii.context.ApplicationContext;
import org.skitii.context.ApplicationContextAware;
import org.skitii.factory.config.BeanPostProcessor;

/**
 * @author skitii
 * @since 2023/10/24
 **/
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext context;

    public ApplicationContextAwareProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof ApplicationContextAware) {
            ApplicationContextAware aware = (ApplicationContextAware) bean;
            aware.setApplicationContext(context);
        }
        return bean;
    }
}
