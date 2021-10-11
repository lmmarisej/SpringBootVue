package org.lmmarise.vue.persistent.org.pojo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/12 12:33 上午
 */
@EntityScan(basePackageClasses = OrgAutoConfiguration.class)
@ComponentScan(basePackageClasses = OrgAutoConfiguration.class)
public class OrgAutoConfiguration {
}
