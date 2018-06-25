package org.litespring.beans.factory.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.exception.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;
import org.litespring.utils.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * XmlBeanDefinitionReader.
 *
 * @author Dulk
 * @version 20180620
 * @date 2018/6/20
 */
public class XmlBeanDefinitionReader {
    private final Log logger = LogFactory.getLog(getClass());

    private static final String BEAN_XML_ATTR_ID = "id";
    private static final String BEAN_XML_ATTR_CLASS = "class";
    private static final String BEAN_XML_ATTR_SCOPE = "scope";
    private static final String BEAN_XML_ATTR_NAME = "name";
    private static final String BEAN_XML_ATTR_VALUE = "value";
    private static final String BEAN_XML_ATTR_REF = "ref";
    private static final String BEAN_XML_ELE_PROPERTY = "property";
    private static final String BEAN_XML_TAG = "bean";

    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanFactory) {
        this.registry = beanFactory;
    }

    public void loadBeanDefinition(Resource resource) {
        InputStream input = null;
        SAXReader saxReader = new SAXReader();
        try {
            input = resource.getInputStream();
            Document document = saxReader.read(input);
            List<Element> beanList = document.getRootElement().elements(BEAN_XML_TAG);
            for (Element e : beanList) {
                String id = e.attributeValue(BEAN_XML_ATTR_ID);
                String className = e.attributeValue(BEAN_XML_ATTR_CLASS);
                BeanDefinition beanDef = new GenericBeanDefinition(id, className);
                if (e.attribute(BEAN_XML_ATTR_SCOPE) != null) {
                    beanDef.setScope(e.attributeValue(BEAN_XML_ATTR_SCOPE));
                }
                parsePropertyElement(e, beanDef);
                registry.registerBeanDefinition(id, beanDef);
            }

        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from " + resource.getDescription(), e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void parsePropertyElement(Element beanEle, BeanDefinition beanDef) {

        Iterator iterator = beanEle.elementIterator(BEAN_XML_ELE_PROPERTY);
        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();
            String propertyName = element.attributeValue(BEAN_XML_ATTR_NAME);
            if (!StringUtil.hasLength(propertyName)) {
                logger.fatal("Tag 'property' must have a 'name' attribute'");
                return;
            }

            Object val = parsePropertyValue(element, propertyName);
            PropertyValue propertyValue = new PropertyValue(propertyName, val);
            beanDef.getPropertyValues().add(propertyValue);
        }

    }

    private Object parsePropertyValue(Element ele, String propertyName) {
        String elementName = propertyName != null ?
                "<property> element for property '" + propertyName + "'" : "<constructor-arg> element";

        boolean hasRefAttribute = ele.attribute(BEAN_XML_ATTR_REF) != null;
        boolean hasValueAttribute = ele.attribute(BEAN_XML_ATTR_VALUE) != null;

        if (hasRefAttribute) {
            String refName = ele.attributeValue(BEAN_XML_ATTR_REF);
            if (!StringUtil.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute");
            }
            RuntimeBeanReference ref = new RuntimeBeanReference(refName);
            return ref;
        }
        else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(ele.attributeValue(BEAN_XML_ATTR_VALUE));
            return valueHolder;
        }
        else {
            throw new RuntimeException(elementName + " must specify a ref or value");
        }
    }

}
