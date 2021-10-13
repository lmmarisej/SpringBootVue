package org.lmmarise.vue.persistent.dao.jpa;

import org.lmmarise.vue.persistent.dao.jpa.repository.BaseRepository;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/13 11:13 下午
 */
@EnableJpaRepositories(basePackageClasses = BaseRepository.class,   // 指定 Repository 所在的包【多个 persistenceUnit 不可共用】
        entityManagerFactoryRef = "entityManagerFactoryBeanOne",
        transactionManagerRef = "platformTransactionManagerOne")
@EnableTransactionManagement
@Configuration
public class PersistenceUnit {

    @Resource
    private JpaProperties jpaProperties;

    @Resource
    private HibernateProperties properties;

    @Resource(name = "dsOne")
    DataSource dsOne;

    @Primary
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanOne(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dsOne)
                .properties(
                        // 将 JPA 和 Hibernate 的配置组合并合并【避免项目 yml 中 JPA 配置不对 Hibernate 生效】
                        properties.determineHibernateProperties(
                                jpaProperties.getProperties(), new HibernateSettings()
                        )
                )
                .packages("org.lmmarise.vue.domain")    // 指定 @Entity 所在的包名
                .persistenceUnit("pu")
                .build();
    }

    @Primary
    @Bean
    PlatformTransactionManager platformTransactionManagerOne(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean factoryOne = entityManagerFactoryBeanOne(builder);
        return new JpaTransactionManager(Objects.requireNonNull(factoryOne.getObject()));
    }
}
