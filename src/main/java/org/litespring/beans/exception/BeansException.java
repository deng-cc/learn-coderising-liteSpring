package org.litespring.beans.exception;

/**
 * BeansException.
 *
 * @author Dulk
 * @version 20180610
 * @date 2018/6/10
 */
public class BeansException extends RuntimeException {
    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
