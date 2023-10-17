package org.skitii.core.io;

/**
 * @author skitii
 * @since 2023/10/17
 **/
public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
