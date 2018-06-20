package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;

/**
 * ClassPathXmlApplicationContext.
 *
 * @author Dulk
 * @version 20180620
 * @date 2018/6/20
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory;

    public ClassPathXmlApplicationContext(String configClasspath) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(configClasspath);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
}
