package org.litespring.beans.factory.support;

import org.litespring.beans.factory.config.SingletonBeanRegistry;
import org.litespring.utils.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DefaultSingletonBeanRegistry.
 *
 * @author Dulk
 * @version 20180621
 * @date 2018/6/21
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>();


    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName, "'beanName' must not be null");
        Object oldObj = this.singletonObjects.get(beanName);
        if (oldObj != null) {
            throw new IllegalArgumentException("Could not register object [" + singletonObject + "] " +
                    "under bean name '" + beanName + "': there is already object [" + oldObj + "] bound");
        }
        this.singletonObjects.put(beanName, singletonObject);
    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }

}
