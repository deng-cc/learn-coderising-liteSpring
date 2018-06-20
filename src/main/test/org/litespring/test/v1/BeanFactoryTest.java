package org.litespring.test.v1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.exception.BeanCreationException;
import org.litespring.beans.factory.exception.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.service.v1.PetStoreService;

import static org.junit.Assert.*;

/**
 * User: Dulk
 * Date: 2018/6/10
 * Time: 11:51
 */
public class BeanFactoryTest {

    DefaultBeanFactory facotry = null;
    XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp() throws Exception {
        facotry = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(facotry);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetBean() {
        Resource resource = new ClassPathResource("petstore-v1.xml");
        reader.loadBeanDefinition(resource);

        BeanDefinition beanDef = facotry.getBeanDefinition("petStore");
        assertEquals("org.litespring.service.v1.PetStoreService", beanDef.getBeanClassName());

        PetStoreService petStore = (PetStoreService) facotry.getBean("petStore");
        assertNotNull(petStore);
    }

    @Test
    public void testInvalidBean() {
        Resource resource = new ClassPathResource("petstore-v1.xml");
        reader.loadBeanDefinition(resource);
        try {
            facotry.getBean("invalidBean");
        } catch (BeanCreationException e) {
            return;
        }
        Assert.fail("expect BeanCreationException");
    }

    @Test
    public void testInvalidXml() {
        try {
            Resource resource = new ClassPathResource("petstore-invalid.xml");
            reader.loadBeanDefinition(resource);
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }

}