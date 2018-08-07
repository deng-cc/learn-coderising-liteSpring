package org.litespring.core.type;

import org.litespring.core.annotation.AnnotationAttributes;

import java.util.Set;

/**
 * User: Dulk
 * Date: 2018/8/7
 * Time: 9:52
 */
public interface AnnotationMetadata extends ClassMetadata {

    Set<String> getAnnotationTypes();

    boolean hasAnnotation(String annotationType);

    AnnotationAttributes getAnnotationAttributes(String annotationType);
}
