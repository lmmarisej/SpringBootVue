package org.lmmarise.vue.web.controller.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 统一 @RestController URL 前缀
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/10 11:42 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ResponseBody   // 避免写成 @RestController，因为 IDEA-Spring 插件的代码检测深度不够
@RequestMapping
public @interface ApiRestController {

    @AliasFor(annotation = RequestMapping.class)
    String name() default "";

    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] path() default {};
}
