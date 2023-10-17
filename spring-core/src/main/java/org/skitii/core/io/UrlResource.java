package org.skitii.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author skitii
 * @since 2023/10/17
 **/
public class UrlResource extends AbstractResource {
    private URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }
}
