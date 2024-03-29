package org.skitii.context;

/**
 * @author skitii
 * @since 2023/10/23
 **/
public interface ConfigurableApplicationContext extends ApplicationContext {
    void refresh();

    void registerShutdownHook();

    void close();

}
