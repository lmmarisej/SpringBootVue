package org.lmmarise.vue.persistent.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lmmarise.vue.domain.Role;
import org.lmmarise.vue.domain.User;

import java.util.List;

@Mapper
public interface UserMapper {

    User loadUserByUsername(String username);

    List<Role> getUserRolesByUid(Integer id);
}
