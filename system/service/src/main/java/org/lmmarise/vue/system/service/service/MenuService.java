package org.lmmarise.vue.system.service.service;

import org.lmmarise.vue.domain.Menu;
import org.lmmarise.vue.persistent.dao.jpa.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/17 9:27 下午
 */
@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu getMenusByMenuId(Integer id) {
        return menuRepository.findById(id).orElse(null);
    }

}
