package org.skitii.context;

import java.util.EventListener;

/**
 * @author skitii
 * @since 2023/10/24
 **/
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event) ;
}
