package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * GenericBeanDefinition.
 *
 * @author Dulk
 * @version 20180610
 * @date 2018/6/10
 */
public class GenericBeanDefinition implements BeanDefinition {

    private String id;
    private String beanClassName;

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    @Override
    public String getBeanClassName() {
        return beanClassName;
    }


}
