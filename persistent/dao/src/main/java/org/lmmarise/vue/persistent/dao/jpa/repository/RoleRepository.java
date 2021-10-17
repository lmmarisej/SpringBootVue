package org.lmmarise.vue.persistent.dao.jpa.repository;

import org.lmmarise.vue.domain.Role;

import java.util.Collection;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/17 12:31 上午
 */
public interface RoleRepository extends BaseRepository<Role, Integer> {

    Role findRoleByName(String name);

    Collection<Role> findRolesByNameIn(Collection<String> name);
}
