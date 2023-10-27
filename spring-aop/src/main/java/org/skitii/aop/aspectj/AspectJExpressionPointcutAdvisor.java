package org.skitii.aop.aspectj;

import org.aopalliance.aop.Advice;
import org.skitii.aop.Pointcut;
import org.skitii.aop.PointcutAdvisor;

/**
 * @author skitii
 * @since 2023/10/26
 **/
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    private AspectJExpressionPointcut pointcut;

    private String expression;
    private Advice advice;

    public void setExpression(String expression){
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
