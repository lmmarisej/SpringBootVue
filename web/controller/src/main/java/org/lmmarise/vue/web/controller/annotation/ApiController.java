package org.lmmarise.vue.web.controller.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 统一 @Controller URL 前缀
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/10 11:42 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@RequestMapping
public @interface ApiController {

    @AliasFor(annotation = RequestMapping.class)
    String name() default "";

    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] path() default {};
}
