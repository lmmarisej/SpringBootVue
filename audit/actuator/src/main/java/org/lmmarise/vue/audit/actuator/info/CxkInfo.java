package org.lmmarise.vue.audit.actuator.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 5:54 下午
 */
@Component
public class CxkInfo implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> info = new HashMap<>();
        info.put("name", "蔡徐坤");
        info.put("email", "cxk@qq.com");
        builder.withDetail("author", info);
    }
}
