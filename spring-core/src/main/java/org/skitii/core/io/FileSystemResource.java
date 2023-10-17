package org.skitii.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author skitii
 * @since 2023/10/17
 **/
public class FileSystemResource extends AbstractResource{
    String path;
    File file;

    public FileSystemResource(String path) {
        this.path = path;
        this.file = new File(path);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }
}
