package org.lmmarise.vue.persistent.dao.jpa.repository1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/12 12:00 上午
 */
@NoRepositoryBean
public interface BaseRepository1<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
