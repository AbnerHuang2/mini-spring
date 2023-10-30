package org.skitii.context.test.converter;

import org.junit.Test;
import org.skitii.context.support.ClassPathXmlApplicationContext;

/**
 * @author skitii
 * @since 2023/10/30
 **/
public class TestConverter {

    @Test
    public void test_convert() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-converter.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        System.out.println("测试结果：" + husband);
    }

}
