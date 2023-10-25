package org.skitii.context.event;

import org.skitii.context.ApplicationContext;
import org.skitii.context.ApplicationEvent;

/**
 * @author skitii
 * @since 2023/10/24
 **/
public abstract class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(ApplicationContext source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }

}
