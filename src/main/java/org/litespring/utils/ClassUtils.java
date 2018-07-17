package org.litespring.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassUtils.
 *
 * @author Dulk
 * @version 20180610
 * @date 2018/6/10
 */
public abstract class ClassUtils {

    private static final Map<Class<?>, Class<?>> wrapperToPrimitiveTypeMap = new HashMap<Class<?>, Class<?>>(8);

    private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new HashMap<Class<?>, Class<?>>(8);

    static {
        wrapperToPrimitiveTypeMap.put(Boolean.class, boolean.class);
        wrapperToPrimitiveTypeMap.put(Byte.class, byte.class);
        wrapperToPrimitiveTypeMap.put(Character.class, char.class);
        wrapperToPrimitiveTypeMap.put(Double.class, double.class);
        wrapperToPrimitiveTypeMap.put(Float.class, float.class);
        wrapperToPrimitiveTypeMap.put(Integer.class, int.class);
        wrapperToPrimitiveTypeMap.put(Long.class, long.class);
        wrapperToPrimitiveTypeMap.put(Short.class, short.class);
        for (Map.Entry<Class<?>, Class<?>> entry : wrapperToPrimitiveTypeMap.entrySet()) {
            primitiveTypeToWrapperMap.put(entry.getValue(), entry.getKey());
        }
    }


    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
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

    public static boolean isAssignableValue(Class<?> type, Object value) {
        Assert.notNull(type, "Type must not be null");
        return (value != null ? isAssignable(type, value.getClass()) : !type.isPrimitive());
    }

    public static boolean isAssignable(Class<?> target, Class<?> value) {
        Assert.notNull(target, "Left-hand side type must not be null");
        Assert.notNull(value, "Right-hand side type must not be null");
        if (target.isAssignableFrom(value)) {
            return true;
        }
        // int k = 5; Class kClass = int.class; kClass.isPrimitive() => true
        if (target.isPrimitive()) {
            Class<?> resolvedPrimitive = wrapperToPrimitiveTypeMap.get(value);
            if (resolvedPrimitive != null && target.equals(resolvedPrimitive)) {
                return true;
            }
        } else {
            Class<?> resolvedWrapper = primitiveTypeToWrapperMap.get(value);
            if (resolvedWrapper != null && target.isAssignableFrom(resolvedWrapper)) {
                return true;
            }
        }
        return false;
    }

}
