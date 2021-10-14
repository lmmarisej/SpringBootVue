package org.lmmarise.vue.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lmmarise.vue.security.model.Role;
import org.lmmarise.vue.security.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    User loadUserByUsername(String username);

    List<Role> getUserRolesByUid(Integer id);
}
