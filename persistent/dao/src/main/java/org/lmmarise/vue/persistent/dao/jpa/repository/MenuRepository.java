package org.lmmarise.vue.persistent.dao.jpa.repository;

import org.lmmarise.vue.domain.Menu;

import java.util.Collection;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/17 5:36 下午
 */
public interface MenuRepository extends BaseRepository<Menu, Integer> {

    Menu findMenuByName(String name);

    Collection<Menu> findMenusByNameIn(Collection<String> names);
}
