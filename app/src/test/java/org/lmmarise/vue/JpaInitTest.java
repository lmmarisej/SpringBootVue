package org.lmmarise.vue;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lmmarise.vue.domain.Hr;
import org.lmmarise.vue.domain.Menu;
import org.lmmarise.vue.domain.Role;
import org.lmmarise.vue.persistent.dao.DaoAutoConfiguration;
import org.lmmarise.vue.persistent.dao.jpa.repository.HrRepository;
import org.lmmarise.vue.persistent.dao.jpa.repository.MenuRepository;
import org.lmmarise.vue.persistent.dao.jpa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 向数据库插入初始化数据
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/17 12:03 上午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DaoAutoConfiguration.class, JpaInitTest.class}, properties = "application.yml")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@SpringBootConfiguration
public class JpaInitTest {

    @Autowired
    private HrRepository hrRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void saveCascadeHr() {
        Menu userMenuChild1 = Menu.builder()
                .name("用户信息1").path("/user/info1/**").build();
        Menu userMenuChild2 = Menu.builder()
                .name("用户信息2").path("/user/info2/**").build();
        Menu userMenuChild3 = Menu.builder()
                .name("用户信息3").path("/user/info3/**").build();
        List<Menu> menus1 = Arrays.asList(userMenuChild1, userMenuChild2, userMenuChild3);

        Menu dbaMenuChild1 = Menu.builder()
                .name("DBA信息1").path("/dba/info1/**").build();
        Menu dbaMenuChild2 = Menu.builder()
                .name("DBA信息2").path("/dba/info2/**").build();
        Menu dbaMenuChild3 = Menu.builder()
                .name("DBA信息3").path("/dba/info3/**").build();
        List<Menu> menus2 = Arrays.asList(dbaMenuChild1, dbaMenuChild2, dbaMenuChild3);

        Menu userMenu = Menu.builder()
                .name("user").path("/user/**").childrenMenu(menus1).build();
        Menu dbaMenu = Menu.builder()
                .name("dba").path("/dba/**").childrenMenu(menus2).build();
        Menu adminMenu = Menu.builder()
                .name("admin").path("/admin/**").build();
        menuRepository.saveAll(Arrays.asList(userMenu, dbaMenu, adminMenu));

        Role userRole = Role.builder()
                .name("USER").nameZh("普通用户")
                .menus(Collections.singleton(userMenu))
                .build();
        Role dbaRole = Role.builder()
                .name("DBA").nameZh("数据管理员")
                .menus(Arrays.asList(userMenu, dbaMenu))
                .build();
        Role adminRole = Role.builder()
                .name("ADMIN").nameZh("管理员")
                .menus(Arrays.asList(userMenu, dbaMenu, adminMenu))
                .build();
        roleRepository.saveAll(Arrays.asList(userRole, dbaRole, adminRole));

        Hr cxk = Hr.builder().name("蔡徐坤")
                .username("cxk").password("{noop}1").roles(Collections.singletonList(userRole)).build();
        Hr dba = Hr.builder().name("数据库管理员")
                .username("root").password("{noop}1").roles(Arrays.asList(userRole, dbaRole)).build();
        Hr admin = Hr.builder().name("系统管理员")
                .username("admin").password("{noop}1").roles(Arrays.asList(userRole, dbaRole, adminRole)).build();
        hrRepository.saveAll(Arrays.asList(cxk, dba, admin));
    }
}
