package org.skitii.context.annotation;

import org.skitii.factory.annotation.Component;
import org.skitii.factory.annotation.Scope;
import org.skitii.factory.config.BeanDefinition;
import org.skitii.factory.support.BeanDefinitionRegistry;

import java.util.Set;

public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider{
    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void scan(String... basePackages) {
        for (String basePackage : basePackages) {
            doScan(basePackage);
        }
    }

    private void doScan(String basePackage) {
        Set<BeanDefinition> beanDefinitions = findCandidateComponents(basePackage);
        for (BeanDefinition beanDefinition : beanDefinitions) {
            // 解析 Bean 的作用域 singleton、prototype
            String beanScope = resolveBeanScope(beanDefinition);
            if (beanScope != null) {
                beanDefinition.setScope(beanScope);
            }
            registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
        }

    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (value == null || value.length() == 0) {
            value = beanClass.getSimpleName();
            value = value.substring(0, 1).toLowerCase() + value.substring(1);
        }
        return value;
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (scope != null) {
            return scope.value();
        }
        return null;
    }

}
