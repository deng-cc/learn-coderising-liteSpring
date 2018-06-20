package org.litespring.beans.factory.config;

import org.litespring.beans.factory.BeanFactory;

/**
 * ConfigurableBeanFactory.
 *
 * @author Dulk
 * @version 20180620
 * @date 2018/6/20
 */
public interface ConfigurableBeanFactory extends BeanFactory {

    void setBeanClassLoader(ClassLoader beanClassLoader);

    ClassLoader getBeanClassLoader();

}
