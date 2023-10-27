package org.skitii.aop.springframework;

import org.skitii.aop.AdvisedSupport;

/**
 * @author skitii
 * @since 2023/10/26
 **/
public class ProxyFactory {
    private AdvisedSupport advisedSupport;
    public ProxyFactory(AdvisedSupport advisedSupport){
        this.advisedSupport = advisedSupport;
    }
    public Object getProxy(){
        return createAopProxy().getProxy();
    }
    private AopProxy createAopProxy(){
        if(advisedSupport.isProxyTargetClass()){
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
