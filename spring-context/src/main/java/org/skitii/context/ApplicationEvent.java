package org.skitii.context;

import java.util.EventObject;

/**
 * @author skitii
 * @since 2023/10/24
 **/
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
