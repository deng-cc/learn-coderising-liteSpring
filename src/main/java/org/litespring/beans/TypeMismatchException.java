package org.litespring.beans;

/**
 * TypeMismatchException.
 *
 * @author Dulk
 * @version 20180717
 * @date 2018/7/17
 */
public class TypeMismatchException extends Exception {

    private transient Object value;

    private Class<?> requiredType;

    public TypeMismatchException(Object value, Class<?> requiredType) {
        super("Failed to convert value: " + value + " to type " + requiredType);
        this.value = value;
        this.requiredType = requiredType;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getRequiredType() {
        return requiredType;
    }
}
