package org.skitii.context.test.factorybean;

import org.skitii.context.test.TestUserMapper;
import org.skitii.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author skitii
 * @since 2023/10/24
 **/
public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    TestUserMapper testUserMapper;

    @Override
    public IUserDao getObject() {
        InvocationHandler handler = (proxy, method, args) -> {
            // 添加排除方法
            if ("toString".equals(method.getName())) return this.toString();

            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("1", "skitii");
            hashMap.put("2", "hello");
            hashMap.put("3", "world");


            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());


        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }
}
