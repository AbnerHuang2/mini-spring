package org.skitii.context.event;

import org.skitii.context.ApplicationEvent;
import org.skitii.context.ApplicationListener;
import org.skitii.factory.BeanFactory;

/**
 * @author skitii
 * @since 2023/10/25
 **/
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }

}
