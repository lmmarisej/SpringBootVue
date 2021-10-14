package org.lmmarise.vue.security.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.lmmarise.vue.security.model.Menu;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<Menu> getAllMenus();
}
