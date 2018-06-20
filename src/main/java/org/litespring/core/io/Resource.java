package org.litespring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: Dulk
 * Date: 2018/6/20
 * Time: 14:11
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

    String getDescription();

}
