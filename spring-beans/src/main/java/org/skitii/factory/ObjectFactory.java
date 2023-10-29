package org.skitii.factory;

import org.skitii.BeansException;

public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
