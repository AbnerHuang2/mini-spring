package org.skitii.context.test.support;

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
        }
    }

    protected abstract String[] getResouresLocations();
}
