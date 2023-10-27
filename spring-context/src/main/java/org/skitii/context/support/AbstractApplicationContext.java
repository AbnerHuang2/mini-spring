package org.skitii.context.support;

import org.skitii.context.ApplicationEvent;
import org.skitii.context.ApplicationListener;
import org.skitii.context.ConfigurableApplicationContext;
import org.skitii.context.event.ApplicationEventMulticaster;
import org.skitii.context.event.ContextClosedEvent;
import org.skitii.context.event.ContextRefreshedEvent;
import org.skitii.context.event.SimpleApplicationEventMulticaster;
import org.skitii.core.io.DefaultResourceLoader;
import org.skitii.factory.ConfigurableListableBeanFactory;
import org.skitii.factory.config.BeanFactoryPostProcessor;
import org.skitii.factory.config.BeanPostProcessor;
import org.skitii.factory.support.DefaultListableBeanFactory;

import java.util.Collection;
import java.util.Map;

/**
 * @author skitii
 * @since 2023/10/23
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";
    private ApplicationEventMulticaster applicationEventMulticaster;

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
        // 5.初始化事件发布者
        initApplicationEventMulticaster();
        // 6.注册事件监听器
        registerListeners();

        // 7.提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

        finishRefresh();
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
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
    public Object getBean(String name, Object... args) {
        return obtainFreshBeanFactory().getBean(name,args);
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
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        // 执行销毁单例bean
        obtainFreshBeanFactory().destroySingletons();
    }
}
