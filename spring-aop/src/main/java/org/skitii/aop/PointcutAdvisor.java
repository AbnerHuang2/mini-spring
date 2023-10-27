package org.skitii.aop;

/**
 * @author skitii
 * @since 2023/10/26
 **/
public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
