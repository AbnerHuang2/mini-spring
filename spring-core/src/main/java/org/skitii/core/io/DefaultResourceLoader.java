package org.skitii.core.io;

import com.sun.istack.internal.Nullable;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author skitii
 * @since 2023/10/17
 **/
public class DefaultResourceLoader implements ResourceLoader{

    @Nullable
    private ClassLoader classLoader;

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    @Override
    public Resource getResource(String location) {
        if (location.startsWith("/")) {
            return new FileSystemResource(location);
        }
        else if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()), null, null);
        }
        else {
            try {
                // Try to parse the location as a URL...
                URL url = new URL(location);
                return new UrlResource(url);
            }
            catch (MalformedURLException ex) {
                // No URL -> resolve as resource path.
                throw new RuntimeException("resource not find");
            }
        }
    }
}
