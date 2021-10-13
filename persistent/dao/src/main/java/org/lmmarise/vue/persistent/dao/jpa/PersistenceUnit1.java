package org.lmmarise.vue.persistent.dao.jpa;

import org.lmmarise.vue.persistent.dao.jpa.repository1.BaseRepository1;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 * @since 2021/10/13 11:14 下午
 */
@EnableJpaRepositories(basePackageClasses = BaseRepository1.class,
        entityManagerFactoryRef = "entityManagerFactoryBeanTwo",
        transactionManagerRef = "platformTransactionManagerTwo")
@EnableTransactionManagement
@Configuration
public class PersistenceUnit1 {

    @Resource
    private JpaProperties jpaProperties;

    @Resource
    private HibernateProperties properties;

    @Resource
    private DataSource dsTwo;

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanTwo(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dsTwo)
                .properties(
                        properties.determineHibernateProperties(
                                jpaProperties.getProperties(),
                                new HibernateSettings()
                        )
                )
                .packages("org.lmmarise.vue.domain")
                .persistenceUnit("pu1")
                .build();
    }

    @Bean
    PlatformTransactionManager platformTransactionManagerTwo(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean factoryOne = entityManagerFactoryBeanTwo(builder);
        return new JpaTransactionManager(Objects.requireNonNull(factoryOne.getObject()));
    }
}
