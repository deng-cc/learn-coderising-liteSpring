package org.litespring.context.support;

import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;

/**
 * ClassPathXmlApplicationContext.
 *
 * @author Dulk
 * @version 20180620
 * @date 2018/6/20
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    public ClassPathXmlApplicationContext(String path) {
        super(path);
    }

    @Override
    Resource getResourceByPath(String path) {
        return new ClassPathResource(path);
    }

}
