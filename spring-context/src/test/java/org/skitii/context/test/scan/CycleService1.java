package org.skitii.context.test.scan;

import org.skitii.context.test.aop.IUserService;
import org.skitii.factory.annotation.Autowired;
import org.skitii.factory.annotation.Component;

@Component
public class CycleService1 {
    @Autowired
    CycleService2 cycleService2;

    @Autowired
    IUserService userService;

    public void hello1() {
        System.out.println("hello1 " + cycleService2 + " " + userService.queryUserInfo());
    }
}
