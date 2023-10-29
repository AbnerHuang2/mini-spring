package org.skitii.context.support;

import org.skitii.context.annotation.ClassPathBeanDefinitionScanner;
import org.skitii.factory.support.BeanDefinitionRegistry;
import org.skitii.factory.support.DefaultListableBeanFactory;
import org.skitii.factory.support.XmlBeanDefinitionReader;

/**
 * @author skitii
 * @since 2023/10/23
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] loctions = getResouresLocations();
        if (loctions != null) {
            beanDefinitionReader.loadBeanDefinitions(loctions);
            String[] basePackages = beanDefinitionReader.getBasePackages(loctions);
            if (basePackages != null) {
                scan(beanFactory, basePackages);
            }
        }
    }

    protected abstract String[] getResouresLocations();

    private void scan(BeanDefinitionRegistry beanDefinitionRegistry, String... basePackages) {
        ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner(beanDefinitionRegistry);
        classPathBeanDefinitionScanner.scan(basePackages);
    }

}
