package org.skitii.context;

import org.skitii.factory.Aware;

/**
 * @author skitii
 * @since 2023/10/24
 **/
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext);
}
