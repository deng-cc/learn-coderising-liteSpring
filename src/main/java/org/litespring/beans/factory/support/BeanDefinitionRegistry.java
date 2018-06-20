package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * User: Dulk
 * Date: 2018/6/20
 * Time: 10:57
 */
public interface BeanDefinitionRegistry {

    BeanDefinition getBeanDefinition(String beanId);

    void registerBeanDefinition(String id, BeanDefinition beanDef);

}
