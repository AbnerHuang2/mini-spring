package org.skitii.context.test.scan;

import org.skitii.factory.annotation.Component;
import org.skitii.context.test.aop.IUserService;
import org.skitii.factory.annotation.Autowired;
import org.skitii.factory.annotation.Value;

@Component
public class ScanService {
    @Value("${age}")
    Integer age;

    @Autowired
    IUserService userService;

    public void hello() {
        System.out.println("hello " + userService.queryUserInfo() + " " + age );
    }

}
