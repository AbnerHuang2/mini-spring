package org.skitii.context.test.event;

import org.skitii.context.ApplicationListener;

/**
 * @author skitii
 * @since 2023/10/25
 **/
public class CustomListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.printf("CustomListener.onApplicationEvent -> CustomEvent: %s%n", event.getMsg());
    }
}
