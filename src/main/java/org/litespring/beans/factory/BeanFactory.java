package org.litespring.beans.factory;

import org.litespring.beans.BeanDefinition;

/**
 * User: Dulk
 * Date: 2018/6/10
 * Time: 14:51
 */
public interface BeanFactory {

    Object getBean(String beanId);

}
