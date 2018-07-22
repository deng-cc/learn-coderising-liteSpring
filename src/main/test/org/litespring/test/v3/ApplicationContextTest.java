package org.litespring.test.v3;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v3.PetStoreService;

/**
 * ApplicationContextTest.
 *
 * @author Dulk
 * @version 20180722
 * @date 2018/7/22
 */
public class ApplicationContextTest {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v3.xml");
        PetStoreService petStore = (PetStoreService) context.getBean("petStore");

        Assert.assertNotNull(petStore.getAccountDao());
        Assert.assertNotNull(petStore.getItemDao());
        Assert.assertEquals(1, petStore.getVersion());
    }

}
