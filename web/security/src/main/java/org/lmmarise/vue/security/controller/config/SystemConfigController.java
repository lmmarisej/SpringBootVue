package org.lmmarise.vue.security.controller.config;

import org.lmmarise.vue.domain.Menu;
import org.lmmarise.vue.domain.Role;
import org.lmmarise.vue.security.domain.HrUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/19 2:41 下午
 */
@RestController
@RequestMapping("/system/config")
public class SystemConfigController {

    /**
     * 根据当前登录用户，动态获取其可访问菜单
     */
    @GetMapping("/menus")
    public Set<Menu> getMenus() {
        Set<Menu> menus = new HashSet<>();
        HrUserDetails hrUserDetails = (HrUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for (Role role : hrUserDetails.getRoles()) {
            menus.addAll(role.getMenus());
        }
        return menus;
    }
}
