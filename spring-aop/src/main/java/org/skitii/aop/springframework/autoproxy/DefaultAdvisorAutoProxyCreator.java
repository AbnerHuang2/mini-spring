package org.skitii.aop.springframework.autoproxy;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.skitii.aop.*;
import org.skitii.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.skitii.aop.springframework.ProxyFactory;
import org.skitii.factory.BeanFactory;
import org.skitii.factory.BeanFactoryAware;
import org.skitii.factory.config.InstantiationAwareBeanPostProcessor;
import org.skitii.factory.support.DefaultListableBeanFactory;

import java.util.Collection;

/**
 * @author skitii
 * @since 2023/10/26
 **/
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        if (isInfrastructureClass(beanClass)) {
            return null;
        }
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor advisor : advisors){
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            // 该bean没有被匹配上，不需要生成代理
            if (!classFilter.matches(beanClass)){
                continue;
            }
            //该bean被aop代理了
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(true);

            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) ||
                Pointcut.class.isAssignableFrom(beanClass) ||
                Advisor.class.isAssignableFrom(beanClass) ;
    }
}
