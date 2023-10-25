package org.skitii.context.test.event;

import org.skitii.context.ApplicationContext;
import org.skitii.context.event.ApplicationContextEvent;

/**
 * @author skitii
 * @since 2023/10/25
 **/
public class CustomEvent extends ApplicationContextEvent {
    String msg;
    public CustomEvent(ApplicationContext source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
