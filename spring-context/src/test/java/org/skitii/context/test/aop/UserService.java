package org.skitii.context.test.aop;

import java.util.Random;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public class UserService implements IUserService {

    private String name;

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name+"，100001，广州";
    }

    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }

}
