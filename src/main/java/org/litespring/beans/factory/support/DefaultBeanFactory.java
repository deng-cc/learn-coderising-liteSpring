package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.beans.factory.exception.BeanCreationException;
import org.litespring.utils.ClassUtil;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DefaultBeanFactory.
 *
 * @author Dulk
 * @version 20180610
 * @date 2018/6/10
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory, BeanDefinitionRegistry {

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
        if (beanDef.isSingleton()) {
            Object bean = this.getSingleton(beanId);
            if (bean == null) {
                bean = createBean(beanDef);
                registerSingleton(beanId, bean);
            }
            return bean;
        }
        return createBean(beanDef);
    }

    private Object createBean(BeanDefinition beanDef) {
        //创建实例
        Object bean = instantiateBean(beanDef);

        //设置属性
        populateBean(beanDef, bean);

        return bean;
    }

    private void populateBean(BeanDefinition beanDef, Object bean) {
        List<PropertyValue> propertyValues = beanDef.getPropertyValues();
        if (propertyValues == null || propertyValues.isEmpty()) {
            return;
        }

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
        try {
            for (PropertyValue propertyValue : propertyValues) {
                String propertyName = propertyValue.getName();
                Object originalValue = propertyValue.getValue();
                Object resolvedValue = resolver.resolveValueIfNecessary(originalValue);
                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor pd : pds) {
                    if (pd.getName().equals(propertyName)) {
                        pd.getWriteMethod().invoke(bean, resolvedValue);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new BeanCreationException("Failed to obtain BeanInfo for class [" + beanDef.getBeanClassName() + "]", e);
        }

    }

    private Object instantiateBean(BeanDefinition beanDef) {
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
