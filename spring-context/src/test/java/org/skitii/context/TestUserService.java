package org.skitii.context;

import org.skitii.factory.BeanNameAware;

/**
 * @author skitii
 * @since 2023/10/13
 **/
public class TestUserService implements BeanNameAware, ApplicationContextAware{
    private String name;
    private int age;
    ApplicationContext applicationContext;
    private TestUserMapper testUserMapper;

    public TestUserService(String name) {
        this.name = name;
    }

    public void hello() {
        System.out.println(String.format("hello world! %s %s %s", name, age, testUserMapper.get("a")));
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
        System.out.println(String.format("TestUserService.setBeanName %s", name));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        System.out.println(String.format("TestUserService.setApplicationContext %s", applicationContext));
    }
}
