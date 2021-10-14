package org.lmmarise.vue.system.service;

import org.lmmarise.vue.persistent.dao.DaoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 4:46 下午
 */
@Configuration
@ComponentScan(basePackageClasses = {ServiceAutoConfiguration.class, DaoAutoConfiguration.class})
public class ServiceAutoConfiguration {
}
