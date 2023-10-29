package org.skitii.context.test.scan;

import org.junit.Test;
import org.skitii.factory.annotation.Component;
import org.skitii.context.support.ClassPathXmlApplicationContext;
import org.skitii.context.test.aop.IUserService;

@Component("testScan")
public class TestScan {

    /**
     * 测试通过xml的component-scan注入bean
     */
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");

        TestScan testScan = (TestScan) applicationContext.getBean("testScan");
        testScan.hello();
    }

    /**
     * 测试通过xml的property注入字符串
     */
    @Test
    public void testPropertiesInject(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");

        IUserService userService = (IUserService) applicationContext.getBean("userService");
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

    /**
     * 测试通过@Value注入常量以及通过@Autowired注入bean
     */
    @Test
    public void testFieldInject(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");

        ScanService scanService = (ScanService) applicationContext.getBean("scanService");
        scanService.hello();
    }

    public void hello() {
        System.out.println("hello");
    }

}
