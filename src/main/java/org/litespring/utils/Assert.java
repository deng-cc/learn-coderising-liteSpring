package org.litespring.utils;

/**
 * Assert.
 *
 * @author Dulk
 * @version 20180620
 * @date 2018/6/20
 */
public abstract class Assert {

    public static void notNull(Object obj, String msg) {
        if (obj == null) {
            throw new IllegalArgumentException(msg);
        }
    }

}
