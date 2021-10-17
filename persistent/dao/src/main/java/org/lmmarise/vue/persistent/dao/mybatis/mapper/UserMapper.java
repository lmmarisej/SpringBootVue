package org.lmmarise.vue.persistent.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lmmarise.vue.domain.Role;
import org.lmmarise.vue.domain.Hr;

import java.util.List;

@Mapper
public interface UserMapper {

    Hr loadUserByUsername(String username);

    List<Role> getHrRolesByUid(Integer id);
}
