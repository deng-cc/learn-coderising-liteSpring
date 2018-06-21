package org.litespring.beans;

/**
 * User: Dulk
 * Date: 2018/6/10
 * Time: 14:53
 */
public interface BeanDefinition {
    String SCOPE_DEFAULT = "";
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    boolean isSingleton();

    boolean isPrototype();

    String getScope();

    void setScope(String scope);

    String getBeanClassName();
}
