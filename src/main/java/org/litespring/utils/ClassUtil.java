package org.litespring.utils;

/**
 * ClassUtil.
 *
 * @author Dulk
 * @version 20180610
 * @date 2018/6/10
 */
public class ClassUtil {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

        if (cl == null) {
            cl = ClassUtil.class.getClassLoader();
            if (cl == null) {
                try {
                    cl = ClassLoader.getSystemClassLoader();

                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
        }

        return cl;
    }

}
