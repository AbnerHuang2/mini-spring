package org.skitii.context.event;

import org.skitii.context.ApplicationContext;

/**
 * @author skitii
 * @since 2023/10/24
 **/
public class ContextRefreshedEvent extends ApplicationContextEvent{

    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }
}
