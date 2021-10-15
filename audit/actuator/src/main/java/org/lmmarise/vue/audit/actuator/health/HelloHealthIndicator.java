package org.lmmarise.vue.audit.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 自定义健康检查，/actuator 开头
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 5:51 下午
 */
@Component
public class HelloHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return Health
                .up().withDetail("msg", "网络连接正常...")
                .status("FATAL").withDetail("msg", "网络断开...")
                .build();
    }
}