package org.litespring.beans.factory.support;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.exception.BeanCreationException;
import org.litespring.beans.factory.exception.BeanDefinitionStoreException;
import org.litespring.utils.ClassUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DefaultBeanFactory.
 *
 * @author Dulk
 * @version 20180610
 * @date 2018/6/10
 */
public class DefaultBeanFactory implements BeanFactory {
    private static final String BEAN_XML_TAG = "bean";
    private static final String BEAN_XML_ATTR_ID = "id";
    private static final String BEAN_XML_ATTR_CLASS = "class";

    private Map<String, BeanDefinition> beanDefs;

    public DefaultBeanFactory(String configClasspath) {
        loadBeanDefinition(configClasspath);
    }

    private void loadBeanDefinition(String configClasspath) {
        beanDefs = new HashMap<String, BeanDefinition>();
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(ClassUtil.getDefaultClassLoader().getResourceAsStream(configClasspath));
            List<Element> beanList = document.getRootElement().elements(BEAN_XML_TAG);
            for (Element e : beanList) {
                String id = e.attributeValue(BEAN_XML_ATTR_ID);
                String className = e.attributeValue(BEAN_XML_ATTR_CLASS);
                beanDefs.put(id, new GenericBeanDefinition(id, className));
            }

        } catch (DocumentException e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document");
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefs.get(beanId);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition beanDef = getBeanDefinition(beanId);
        if (beanDef == null) {
            throw new BeanCreationException("Bean Definition does not exist");
        }
        ClassLoader classLoader = ClassUtil.getDefaultClassLoader();
        try {
            Class<?> clazz = classLoader.loadClass(beanDef.getBeanClassName());
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanDef.getBeanClassName() + "failed");
        }
    }
}
