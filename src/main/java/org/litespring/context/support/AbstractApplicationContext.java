package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;

/**
 * AbstractApplicationContext.
 *
 * @author Dulk
 * @version 20180620
 * @date 2018/6/20
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory;

    public AbstractApplicationContext(String path) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = getResourceByPath(path);
        reader.loadBeanDefinition(resource);
    }

    abstract Resource getResourceByPath(String path);

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

}
