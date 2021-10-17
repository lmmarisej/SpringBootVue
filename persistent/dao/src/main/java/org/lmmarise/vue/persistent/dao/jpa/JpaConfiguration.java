package org.lmmarise.vue.persistent.dao.jpa;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/13 11:30 下午
 */
@EnableAutoConfiguration
@Configuration
@EnableJpaAuditing
public class JpaConfiguration {
}
