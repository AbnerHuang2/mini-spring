package org.skitii.context.event;

import org.skitii.context.ApplicationContext;

public class ContextClosedEvent extends ApplicationContextEvent{

    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
