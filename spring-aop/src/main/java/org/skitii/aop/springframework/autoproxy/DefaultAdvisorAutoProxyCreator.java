package org.skitii.aop.springframework.autoproxy;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.skitii.BeansException;
import org.skitii.aop.*;
import org.skitii.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.skitii.aop.springframework.ProxyFactory;
import org.skitii.factory.BeanFactory;
import org.skitii.factory.BeanFactoryAware;
import org.skitii.factory.PropertyValues;
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
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        Class beanClass = bean.getClass();
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
            TargetSource targetSource = new TargetSource(bean);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getProxy();
        }
        return bean;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) ||
                Pointcut.class.isAssignableFrom(beanClass) ||
                Advisor.class.isAssignableFrom(beanClass) ;
    }
}
