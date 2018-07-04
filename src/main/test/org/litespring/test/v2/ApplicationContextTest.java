package org.litespring.test.v2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.dao.v2.AccountDao;
import org.litespring.dao.v2.ItemDao;
import org.litespring.service.v2.PetStoreService;


/**
 * User: Dulk
 * Date: 2018/6/25
 * Time: 09:32
 */
public class ApplicationContextTest {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetBeanProperty() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStoreService petStore = (PetStoreService) applicationContext.getBean("petStore");

        Assert.assertNotNull(petStore.getAccountDao());
        Assert.assertNotNull(petStore.getItemDao());
        Assert.assertTrue(petStore.getAccountDao() instanceof AccountDao);
        Assert.assertTrue(petStore.getItemDao() instanceof ItemDao);

        Assert.assertEquals("liuxin", petStore.getOwner());

    }

}