package org.skitii.aop;

import org.aopalliance.aop.Advice;

/**
 *  Base interface holding AOP <b>advice</b> (action to take at a joinpoint)
 * and a filter determining the applicability of the advice (such as
 * a pointcut). <i>This interface is not for use by Spring users, but to
 * allow for commonality in support for different types of advice.</i>
 * @author skitii
 * @since 2023/10/26
 **/
public interface Advisor {
    Advice getAdvice();
}
