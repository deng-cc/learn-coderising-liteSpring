package org.litespring.core.type.classreading;

import org.litespring.core.io.Resource;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.core.type.ClassMetadata;

/**
 * MetadataReader.
 *
 * @author Dulk
 * @version 20180807
 * @date 2018/8/7
 */
public interface MetadataReader {
    /**
     * Return the resource reference for the class file.
     */
    Resource getResource();

    /**
     * Read basic class metadata for the underlying class.
     */
    ClassMetadata getClassMetadata();

    /**
     * Read full annotation metadata for the underlying class,
     * including metadata for annotated methods.
     */
    AnnotationMetadata getAnnotationMetadata();

}
