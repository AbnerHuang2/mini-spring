package org.skitii.context.event;

import org.skitii.context.ApplicationEvent;
import org.skitii.context.ApplicationListener;

/**
 * Interface to be implemented by objects that can manage a number of
 *  * {@link org.skitii.context.ApplicationListener} objects, and publish events to them.
 *  
 * @author skitii
 * @since 2023/10/24
 **/
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);
    
    void removeAllListeners();

    void multicastEvent(ApplicationEvent event);
}
