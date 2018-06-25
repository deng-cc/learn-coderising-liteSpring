package org.litespring.beans;

/**
 * PropertyValue.
 *
 * @author Dulk
 * @version 20180625
 * @date 2018/6/25
 */
public class PropertyValue {

    private final String name;
    private final Object value;
    private Object convertedValue;
    private boolean converted = false;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public synchronized boolean isConverted() {
        return converted;
    }

    public synchronized Object getConvertedValue() {
        return convertedValue;
    }

    public synchronized void setConvertedValue(Object convertedValue) {
        this.convertedValue = convertedValue;
    }
}
