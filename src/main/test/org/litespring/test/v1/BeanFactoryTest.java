package org.litespring.test.v1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.litespring.service.v1.PetStoreService;

import static org.junit.Assert.*;

/**
 * User: Dulk
 * Date: 2018/6/10
 * Time: 11:51
 */
public class BeanFactoryTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetBean(){
        BeanFactory facotry = new DefaultBeanFactory("petstore-v1.xml");
        BeanDefinition beanDef = facotry.getBeanDefinition("petStore");

        assertEquals("org.litespring.service.v1.PetStoreService", beanDef.getBeanClassName());

        PetStoreService petStore = (PetStoreService) facotry.getBean("petStore");
        assertNotNull(petStore);
    }

}