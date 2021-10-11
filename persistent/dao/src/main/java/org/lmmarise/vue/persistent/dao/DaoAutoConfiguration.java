package org.lmmarise.vue.persistent.dao;

import org.lmmarise.vue.persistent.dao.jpa.BaseRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 7:14 下午
 */
@EnableJpaRepositories(basePackageClasses = BaseRepository.class)
@MapperScan(value = {"org.lmmarise.vue.persistent.dao.mapper"})
@ComponentScan(basePackageClasses = {
        DaoAutoConfiguration.class
})
public class DaoAutoConfiguration {
}
