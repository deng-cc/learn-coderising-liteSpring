package org.litespring.beans.factory.exception;

import org.litespring.beans.exception.BeansException;

/**
 * BeanCreationException.
 *
 * @author Dulk
 * @version 20180610
 * @date 2018/6/10
 */
public class BeanCreationException extends BeansException {
    private String beanName;

    public BeanCreationException(String message) {
        super(message);
    }

    public BeanCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanCreationException(String beanName, String message) {
        super("Error creating bean with name '" + beanName + "':" + message);
        this.beanName = beanName;
    }

    public BeanCreationException(String beanName, String message, Throwable cause) {
        this(beanName, message);
        initCause(cause);
    }

    public String getBeanName() {
        return beanName;
    }
}
