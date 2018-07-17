package org.litespring.test.v2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;

import java.util.List;

/**
 * BeanDefinitionTest.
 *
 * @author Dulk
 * @version 20180625
 * @date 2018/6/25
 */
public class BeanDefinitionTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetBeanDefinition() {

        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v2.xml"));

        BeanDefinition beanDef = factory.getBeanDefinition("petStore");

        List<PropertyValue> valueList = beanDef.getPropertyValues();
        Assert.assertTrue(valueList.size() == 4);

        {
            PropertyValue value = this.getPropertyValue("accountDao", valueList);
            Assert.assertNotNull(value);
            Assert.assertTrue(value.getValue() instanceof RuntimeBeanReference);
        }

        {
            PropertyValue value = this.getPropertyValue("itemDao", valueList);
            Assert.assertNotNull(value);
            Assert.assertTrue(value.getValue() instanceof RuntimeBeanReference);
        }

    }

    private PropertyValue getPropertyValue(String name, List<PropertyValue> valueList) {
        for (PropertyValue value : valueList) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        return null;
    }


}
