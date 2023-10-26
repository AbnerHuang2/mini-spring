package org.skitii.aop.test.bean;

import java.util.Random;

/**
 * @author skitii
 * @since 2023/10/26
 **/
public class UserService implements IUserService{
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "skitii，100001，广州";
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
