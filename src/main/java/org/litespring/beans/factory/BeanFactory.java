package org.litespring.beans.factory;

import org.litespring.beans.BeanDefinition;

/**
 * User: Dulk
 * Date: 2018/6/10
 * Time: 14:51
 */
public interface BeanFactory {
    BeanDefinition getBeanDefinition(String beanId);

    Object getBean(String beanId);

    void registerBeanDefinition(String id, BeanDefinition beanDef);
}
