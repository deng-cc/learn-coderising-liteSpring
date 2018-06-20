package org.litespring.test.v1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;


/**
 * User: Dulk
 * Date: 2018/6/10
 * Time: 11:51
 */
public class ResourceTest {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testClassPathResource() throws IOException {
        Resource resource = new ClassPathResource("petstore-v1.xml");
        InputStream in = null;
        try {
            in = resource.getInputStream();
            Assert.assertNotNull(in);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    @Test
    public void testFileSystemResource() throws IOException {
        Resource resource = new FileSystemResource("C:\\workspace\\project\\learning\\liteSpring\\src\\main\\test-resource\\petstore-v1.xml");
        InputStream in = null;
        try {
            in = resource.getInputStream();
            Assert.assertNotNull(in);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

}