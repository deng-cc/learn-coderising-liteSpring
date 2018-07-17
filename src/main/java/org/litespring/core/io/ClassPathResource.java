package org.litespring.core.io;

import org.litespring.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * ClassPathResource.
 *
 * @author Dulk
 * @version 20180620
 * @date 2018/6/20
 */
public class ClassPathResource implements Resource {

    private String path;
    private ClassLoader classLoader;

    public ClassPathResource(String configClasspath) {
        this.path = configClasspath;
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader == null ? ClassUtils.getDefaultClassLoader() : classLoader;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream in = classLoader.getResourceAsStream(path);
        if (in == null) {
            throw new FileNotFoundException(path + " cannot be opened");
        }

        return in;
    }

    @Override
    public String getDescription() {
        return path;
    }
}
