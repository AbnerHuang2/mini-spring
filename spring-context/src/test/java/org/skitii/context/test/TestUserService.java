package org.skitii.context.test;

/**
 * @author skitii
 * @since 2023/10/13
 **/
public class TestUserService {
    private String name;
    private int age;

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
}
