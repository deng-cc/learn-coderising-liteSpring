package org.litespring.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.litespring.test.v1.AllTestV1;
import org.litespring.test.v2.AllTestV2;
import org.litespring.test.v3.AllTestV3;

/**
 * AllTests.
 *
 * @author Dulk
 * @version 20180717
 * @date 2018/7/17
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllTestV1.class,
        AllTestV2.class,
        AllTestV3.class
})
public class AllTests {
}
