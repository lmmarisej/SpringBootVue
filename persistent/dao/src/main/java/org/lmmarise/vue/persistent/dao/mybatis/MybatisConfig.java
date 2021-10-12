package org.lmmarise.vue.persistent.dao.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/12 2:23 下午
 */
@MapperScan(value = {"org.lmmarise.vue.persistent.dao.mybatis.mapper"},
        sqlSessionFactoryRef = "sqlSessionFactoryBean")
@MapperScan(value = {"org.lmmarise.vue.persistent.dao.mybatis.mapper1"},
        sqlSessionFactoryRef = "sqlSessionFactoryBean1")
@Configuration
public class MybatisConfig {

    @Resource
    DataSource dsOne;

    @Resource
    DataSource dsTwo;

    @Bean
    SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dsOne);
        return factoryBean.getObject();
    }

    @Bean
    SqlSessionFactory sqlSessionFactoryBean1() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dsTwo);
        return factoryBean.getObject();
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryBean());
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryBean1());
    }
}
