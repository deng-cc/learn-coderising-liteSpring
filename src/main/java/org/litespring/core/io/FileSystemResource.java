package org.litespring.core.io;

import org.litespring.utils.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * FileSystemResource.
 *
 * @author Dulk
 * @version 20180620
 * @date 2018/6/20
 */
public class FileSystemResource implements Resource {

    private final String path;
    private final File file;

    public FileSystemResource(File file) {
        this.path = file.getPath();
        this.file = file;
    }

    public FileSystemResource(String configFilePath) {
        Assert.notNull(configFilePath, "path must not be null");
        this.path = configFilePath;
        this.file = new File(configFilePath);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public String getDescription() {
        return "file [" + this.file.getAbsolutePath() + "]";
    }

}
