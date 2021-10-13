package org.lmmarise.vue.cache.mongodb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注 MongoDB 实体主键，以自增
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/13 6:00 下午
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoIncKey {
}
