package org.litespring.test.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.litespring.test.v1.*;

/**
 * AllTestV2.
 *
 * @author Dulk
 * @version 20180717
 * @date 2018/7/17
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        org.litespring.test.v2.ApplicationContextTest.class,
        BeanDefinitionTest.class,
        BeanDefinitionValueResolverTest.class,
        CustomBooleanEditorTest.class,
        CustomNumberEditorTest.class,
        TypeConverterTest.class
})
public class AllTestV2 {
}
