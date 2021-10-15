package org.lmmarise.vue.persistent.dao.mybatis.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.lmmarise.vue.domain.Menu;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<Menu> getAllMenus();

    Integer getRoleIdByName(String roleName);

    void saveMenuRole(Integer menuId, Integer roleId);

    Integer saveMenu(String menuPattern);

    Integer saveRole(String roleName);
}
