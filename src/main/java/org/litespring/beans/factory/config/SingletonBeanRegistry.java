package org.litespring.beans.factory.config;

/**
 * User: Dulk
 * Date: 2018/6/21
 * Time: 10:14
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

}
