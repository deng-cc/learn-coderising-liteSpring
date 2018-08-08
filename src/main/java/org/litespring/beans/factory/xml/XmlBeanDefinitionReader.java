package org.litespring.beans.factory.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.exception.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.context.annotation.ClassPathBeanDefinitionScanner;
import org.litespring.core.io.Resource;
import org.litespring.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * XmlBeanDefinitionReader.
 *
 * @author Dulk
 * @version 20180620
 * @date 2018/6/20
 */
public class XmlBeanDefinitionReader {
    private final Log logger = LogFactory.getLog(getClass());

    private static final String ID_ATTRIBUTE = "id";
    private static final String CLASS_ATTRIBUTE = "class";
    private static final String SCOPE_ATTRIBUTE = "scope";
    private static final String NAME_ATTRIBUTE = "name";
    private static final String VALUE_ATTRIBUTE = "value";
    private static final String REF_ATTRIBUTE = "ref";
    private static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";
    private static final String TYPE_ATTRIBUTE = "type";
    private static final String PROPERTY_ELEMENT = "property";
    private static final String BEANS_NAMESPACE_URI = "http://www.springframework.org/schema/beans";
    private static final String CONTEXT_NAMESPACE_URI = "http://www.springframework.org/schema/context";
    private static final String BASE_PACKAGE_ATTRIBUTE = "base-package";

    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanFactory) {
        this.registry = beanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        InputStream is = null;
        try {
            is = resource.getInputStream();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);

            Element root = doc.getRootElement(); //<beans>
            Iterator<Element> iter = root.elementIterator();
            while (iter.hasNext()) {
                Element ele = (Element) iter.next();
                String namespaceUri = ele.getNamespaceURI();
                if (this.isDefaultNamespace(namespaceUri)) {
                    parseDefaultElement(ele); //普通的bean
                } else if (this.isContextNamespace(namespaceUri)) {
                    parseComponentElement(ele); //例如<context:component-scan>
                }
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from " + resource.getDescription(), e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void parseComponentElement(Element ele) {
        String basePackages = ele.attributeValue(BASE_PACKAGE_ATTRIBUTE);
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.doScan(basePackages);

    }

    private void parseDefaultElement(Element ele) {
        String id = ele.attributeValue(ID_ATTRIBUTE);
        String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
        BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
        if (ele.attribute(SCOPE_ATTRIBUTE) != null) {
            bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));
        }
        parseConstructorArgElements(ele, bd);
        parsePropertyElement(ele, bd);
        this.registry.registerBeanDefinition(id, bd);

    }

    public boolean isDefaultNamespace(String namespaceUri) {
        return (!StringUtils.hasLength(namespaceUri) || BEANS_NAMESPACE_URI.equals(namespaceUri));
    }

    public boolean isContextNamespace(String namespaceUri) {
        return (!StringUtils.hasLength(namespaceUri) || CONTEXT_NAMESPACE_URI.equals(namespaceUri));
    }

    private void parseConstructorArgElements(Element beanEle, BeanDefinition beanDef) {
        Iterator iterator = beanEle.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
        while (iterator.hasNext()) {
            Element ele = (Element) iterator.next();
            parseConstructorArgElement(ele, beanDef);
        }

    }

    private void parseConstructorArgElement(Element ele, BeanDefinition beanDef) {
        String typeAttr = ele.attributeValue(TYPE_ATTRIBUTE);
        String nameAttr = ele.attributeValue(NAME_ATTRIBUTE);
        Object value = parsePropertyValue(ele, null);
        ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
        if (StringUtils.hasLength(typeAttr)) {
            valueHolder.setType(typeAttr);
        }
        if (StringUtils.hasLength(nameAttr)) {
            valueHolder.setName(nameAttr);
        }
        beanDef.getConstructorArgument().addArgumentValue(valueHolder);
    }


    private void parsePropertyElement(Element beanEle, BeanDefinition beanDef) {

        Iterator iterator = beanEle.elementIterator(PROPERTY_ELEMENT);
        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();
            String propertyName = element.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)) {
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

        boolean hasRefAttribute = ele.attribute(REF_ATTRIBUTE) != null;
        boolean hasValueAttribute = ele.attribute(VALUE_ATTRIBUTE) != null;

        if (hasRefAttribute) {
            String refName = ele.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute");
            }
            RuntimeBeanReference ref = new RuntimeBeanReference(refName);
            return ref;
        } else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));
            return valueHolder;
        } else {
            //todo
            throw new RuntimeException(elementName + " must specify a ref or value");
        }
    }

}
