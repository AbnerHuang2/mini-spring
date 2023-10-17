package org.skitii.factory.support;

import org.skitii.core.io.DefaultResourceLoader;
import org.skitii.core.io.ResourceLoader;

/**
 * @author skitii
 * @since 2023/10/17
 **/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
