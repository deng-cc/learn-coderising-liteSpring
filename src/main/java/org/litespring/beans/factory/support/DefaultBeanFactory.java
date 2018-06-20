package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.exception.BeanCreationException;
import org.litespring.utils.ClassUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * DefaultBeanFactory.
 *
 * @author Dulk
 * @version 20180610
 * @date 2018/6/10
 */
public class DefaultBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefs;

    public DefaultBeanFactory() {
        this.beanDefs = new HashMap<String, BeanDefinition>();
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefs.get(beanId);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition beanDef = getBeanDefinition(beanId);
        if (beanDef == null) {
            throw new BeanCreationException("Bean Definition does not exist");
        }
        ClassLoader classLoader = ClassUtil.getDefaultClassLoader();
        try {
            Class<?> clazz = classLoader.loadClass(beanDef.getBeanClassName());
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanDef.getBeanClassName() + "failed");
        }
    }

    @Override
    public void registerBeanDefinition(String id, BeanDefinition beanDef) {
        beanDefs.put(id, beanDef);
    }
}
