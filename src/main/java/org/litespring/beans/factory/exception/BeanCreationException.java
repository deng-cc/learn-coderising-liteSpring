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

    public BeanCreationException(String message) {
        super(message);
    }

    public BeanCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
