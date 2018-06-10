package org.litespring.beans.factory.exception;

import org.litespring.beans.exception.BeansException;

/**
 * BeanDefinitionStoreException.
 *
 * @author Dulk
 * @version 20180610
 * @date 2018/6/10
 */
public class BeanDefinitionStoreException extends BeansException {

    public BeanDefinitionStoreException(String message) {
        super(message);
    }

    public BeanDefinitionStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
