package org.litespring.beans;

/**
 * User: Dulk
 * Date: 2018/7/17
 * Time: 15:37
 */
public interface TypeConverter {

    <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException;

}
