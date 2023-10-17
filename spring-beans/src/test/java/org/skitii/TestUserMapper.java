package org.skitii;

import java.util.HashMap;
import java.util.Map;

/**
 * @author skitii
 * @since 2023/10/17
 **/
public class TestUserMapper {
    private static final Map<String,String> map = new HashMap<>();
    static {
        map.put("a","1");
        map.put("b","2");
        map.put("c","3");
    }

    public String get(String key){
        return map.get(key);
    }

}
