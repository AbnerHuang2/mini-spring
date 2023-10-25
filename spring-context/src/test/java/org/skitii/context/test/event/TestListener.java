package org.skitii.context.test.event;

import org.skitii.context.ApplicationListener;
import org.skitii.context.event.ContextRefreshedEvent;

/**
 * @author skitii
 * @since 2023/10/25
 **/
public class TestListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.print("TestListener.onApplicationEvent -> ContextRefreshedEvent %n");
    }
}
