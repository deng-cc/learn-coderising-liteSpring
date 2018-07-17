package org.litespring.test.v2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.propertyeditors.CustomNumberEditor;

/**
 * CustomNumberEditorTest.
 *
 * @author Dulk
 * @version 20180704
 * @date 2018/7/4
 */
public class CustomNumberEditorTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testConvertString() {
        CustomNumberEditor editor = new CustomNumberEditor(Integer.class, true);
        editor.setAsText("3");
        Object value = editor.getValue();
        Assert.assertTrue(value instanceof Integer);
        Assert.assertEquals(3, ((Integer)editor.getValue()).intValue());

        editor.setAsText("");
        Assert.assertTrue(editor.getValue() == null);

        try {
            editor.setAsText("3.1");
        } catch (IllegalArgumentException e) {
            return;
        }

        Assert.fail();
    }

}
