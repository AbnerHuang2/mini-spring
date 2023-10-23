package org.skitii.factory;

/**
 * @author skitii
 * @since 2023/10/23
 **/
public interface InitializingBean {
    /**
     * Bean初始化方法
     * 属性设置完成后调用
     */
    void afterPropertiesSet();
}
