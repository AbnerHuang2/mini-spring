package org.skitii.factory;

/**
 * @author skitii
 * @since 2023/10/23
 **/
public interface DisposableBean {
    /**
     * Bean销毁方法
     * Bean销毁前调用
     */
    void destroy();
}
