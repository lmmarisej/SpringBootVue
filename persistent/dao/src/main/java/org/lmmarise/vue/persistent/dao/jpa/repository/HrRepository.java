package org.lmmarise.vue.persistent.dao.jpa.repository;

import org.lmmarise.vue.domain.Hr;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/17 12:04 上午
 */
public interface HrRepository extends BaseRepository<Hr, Integer> {

    Hr findHrByUsername(String username);
}
