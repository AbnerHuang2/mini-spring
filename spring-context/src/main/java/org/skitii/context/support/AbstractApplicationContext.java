package org.skitii.context.support;

import org.skitii.context.ConfigurableApplicationContext;
import org.skitii.core.io.DefaultResourceLoader;
import org.skitii.factory.ConfigurableListableBeanFactory;
import org.skitii.factory.config.BeanFactoryPostProcessor;
import org.skitii.factory.config.BeanPostProcessor;
import org.skitii.factory.support.DefaultListableBeanFactory;

import java.util.Map;

/**
 * @author skitii
 * @since 2023/10/23
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() {
        // 1.创建BeanFactory，加载BeanDefinition
        refreshBeanFactory();
        // 2.获取BeanFactory
        DefaultListableBeanFactory beanFactory = obtainFreshBeanFactory();
        // 3.执行beanFactoryPostProcessors
        invokeBeanFactoryPostProcessors(beanFactory);
        // 4.注册BeanPostProcessor
        registerBeanPostProcessors(beanFactory);
        // 5.提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        // 注入applicationContext感知处理器
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        //注入beanPostProcessor
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    protected abstract DefaultListableBeanFactory obtainFreshBeanFactory();

    protected abstract void refreshBeanFactory();

    @Override
    public Object getBean(String name) {
        return obtainFreshBeanFactory().getBean(name);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return obtainFreshBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return obtainFreshBeanFactory().getBeansOfType(type);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        obtainFreshBeanFactory().destroySingletons();
    }
}
