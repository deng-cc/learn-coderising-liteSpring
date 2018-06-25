package org.litespring.beans.factory.config;

/**
 * RuntimeBeanReference.
 *
 * @author Dulk
 * @version 20180625
 * @date 2018/6/25
 */
public class RuntimeBeanReference {

    private final String beanName;

    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
