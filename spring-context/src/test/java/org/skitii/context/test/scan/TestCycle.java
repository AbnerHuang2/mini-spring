package org.skitii.context.test.scan;

import org.junit.Test;
import org.skitii.context.support.ClassPathXmlApplicationContext;

public class TestCycle {
    @Test
    public void testCycle() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-cycle.xml");

        CycleService1 cycleService1 = (CycleService1) applicationContext.getBean("cycleService1");
        cycleService1.hello1();
    }
}
