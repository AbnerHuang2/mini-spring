package org.skitii.context.support;

import org.skitii.factory.support.DefaultListableBeanFactory;

/**
 * @author skitii
 * @since 2023/10/23
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;
    @Override
    protected DefaultListableBeanFactory obtainFreshBeanFactory() {
        return beanFactory;
    }

    @Override
    protected void refreshBeanFactory() {
        beanFactory = new DefaultListableBeanFactory();
        loadBeanDefinitions(beanFactory);
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

}
