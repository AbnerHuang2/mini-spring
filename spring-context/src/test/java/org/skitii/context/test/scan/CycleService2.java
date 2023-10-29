package org.skitii.context.test.scan;

import org.skitii.factory.annotation.Autowired;
import org.skitii.factory.annotation.Component;

@Component
public class CycleService2 {
    @Autowired
    CycleService1 cycleService1;

    public void hello2() {
        System.out.println("hello2 " + cycleService1);
    }

}
