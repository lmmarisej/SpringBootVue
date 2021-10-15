package org.lmmarise.vue.system.service.service;

import org.lmmarise.vue.persistent.dao.mybatis.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 7:36 下午
 */
@Service
public class RbacMenuService {

    @Autowired
    private MenuMapper menuMapper;

    public void addMenu(String menuPattern, String role) {
        Integer menusId = menuMapper.saveMenu(menuPattern);
        Integer roleId = menuMapper.getRoleIdByName(role);
        if (roleId == null) {
            roleId = menuMapper.saveRole(role);
        }
        menuMapper.saveMenuRole(menusId, roleId);
    }
}
