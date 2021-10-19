package org.lmmarise.vue.system.service.service;

import org.lmmarise.vue.domain.Hr;
import org.lmmarise.vue.persistent.dao.jpa.repository.HrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/17 9:35 下午
 */
@Service
public class HrService {

    @Autowired
    private HrRepository hrRepository;

    public Hr loadUserByUsername(String username) {
        return getHrByUsername(username);
    }

    public Hr getHrByUsername(String username) {
        return hrRepository.findHrByUsername(username);
    }

    public Hr getHrById(Integer id) {
        return hrRepository.findById(id).orElse(null);
    }
}
