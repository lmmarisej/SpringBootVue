package org.lmmarise.vue.web.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/10 8:16 下午 ErrorMvcAutoConfiguration.class
 */
@ComponentScan(basePackageClasses = WebAutoConfiguration.class,
        excludeFilters = {@ComponentScan.Filter(
                // 不使用SpringBoot自动配置的错误页
                type = FilterType.ASSIGNABLE_TYPE, classes = {ErrorMvcAutoConfiguration.class})
        }
)
public class WebAutoConfiguration {
}
