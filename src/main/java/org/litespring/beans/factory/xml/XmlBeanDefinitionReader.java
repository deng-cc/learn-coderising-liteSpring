package org.litespring.beans.factory.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.factory.exception.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.utils.ClassUtil;

import java.util.List;

/**
 * XmlBeanDefinitionReader.
 *
 * @author Dulk
 * @version 20180620
 * @date 2018/6/20
 */
public class XmlBeanDefinitionReader {

    private static final String BEAN_XML_ATTR_ID = "id";
    private static final String BEAN_XML_ATTR_CLASS = "class";
    private static final String BEAN_XML_TAG = "bean";

    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanFactory) {
        this.registry = beanFactory;
    }

    public void loadBeanDefinition(String configClasspath) {
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(ClassUtil.getDefaultClassLoader().getResourceAsStream(configClasspath));
            List<Element> beanList = document.getRootElement().elements(BEAN_XML_TAG);
            for (Element e : beanList) {
                String id = e.attributeValue(BEAN_XML_ATTR_ID);
                String className = e.attributeValue(BEAN_XML_ATTR_CLASS);
                registry.registerBeanDefinition(id, new GenericBeanDefinition(id, className));
            }

        } catch (DocumentException e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document");
        }
    }

}
