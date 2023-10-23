package org.skitii.context.test;

import org.skitii.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author skitii
 * @since 2023/10/17
 **/
public class TestUserMapper implements InitializingBean {
    private static final Map<String,String> map = new HashMap<>();
    static {
        map.put("a","1");
        map.put("b","2");
        map.put("c","3");
    }

    public String get(String key){
        return map.get(key);
    }

    public void initDataMethod(){
        System.out.println("TestUserMapper.initDataMethod");
    }
    public void destroyDataMethod(){
        System.out.println("TestUserMapper.destroyDataMethod");
    }


    @Override
    public void afterPropertiesSet() {
        System.out.println("TestUserMapper.afterPropertiesSet");
    }
}
