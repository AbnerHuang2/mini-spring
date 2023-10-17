package org.skitii.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author skitii
 * @since 2023/10/17
 **/
public interface InputStreamSource {
    InputStream getInputStream() throws IOException;
}
