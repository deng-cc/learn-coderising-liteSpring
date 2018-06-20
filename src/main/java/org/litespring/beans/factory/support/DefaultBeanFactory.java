package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
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
public class DefaultBeanFactory implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefs;
    private ClassLoader beanClassLoader;

    public DefaultBeanFactory() {
        this.beanDefs = new HashMap<String, BeanDefinition>();
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition beanDef = getBeanDefinition(beanId);
        if (beanDef == null) {
            throw new BeanCreationException("Bean Definition does not exist");
        }
        ClassLoader classLoader = getBeanClassLoader();
        try {
            Class<?> clazz = classLoader.loadClass(beanDef.getBeanClassName());
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanDef.getBeanClassName() + "failed");
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefs.get(beanId);
    }

    @Override
    public void registerBeanDefinition(String id, BeanDefinition beanDef) {
        beanDefs.put(id, beanDef);
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return beanClassLoader == null ? ClassUtil.getDefaultClassLoader() : beanClassLoader;
    }
}
