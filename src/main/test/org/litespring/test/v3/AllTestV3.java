package org.litespring.test.v3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * AllTestV3.
 *
 * @author Dulk
 * @version 20180722
 * @date 2018/7/22
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTest.class,
        BeanDefinitionTest.class,
        ConstructorResolverTest.class
})
public class AllTestV3 {
}
