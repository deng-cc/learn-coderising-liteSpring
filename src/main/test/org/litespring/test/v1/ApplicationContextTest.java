package org.litespring.test.v1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.context.support.FileSystemXmlApplicationContext;
import org.litespring.service.v1.PetStoreService;


/**
 * User: Dulk
 * Date: 2018/6/10
 * Time: 11:51
 */
public class ApplicationContextTest {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetBeanFromClassPathXmlContext() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStore = (PetStoreService) applicationContext.getBean("petStore");
        Assert.assertNotNull(petStore);
    }

    @Test
    public void testGetBeanFromFileSystemXmlContext() {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("C:\\workspace\\project\\learning\\liteSpring\\src\\main\\test-resource\\petstore-v1.xml");
        PetStoreService petStore = (PetStoreService) applicationContext.getBean("petStore");
        Assert.assertNotNull(petStore);
    }


}