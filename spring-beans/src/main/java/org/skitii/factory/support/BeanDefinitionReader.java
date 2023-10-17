package org.skitii.factory.support;

import com.sun.istack.internal.Nullable;
import org.skitii.core.io.Resource;
import org.skitii.core.io.ResourceLoader;

/**
 * @author skitii
 * @since 2023/10/17
 **/
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    @Nullable
    ResourceLoader getResourceLoader();

    int loadBeanDefinitions(Resource resource);

    int loadBeanDefinitions(String location);
}
