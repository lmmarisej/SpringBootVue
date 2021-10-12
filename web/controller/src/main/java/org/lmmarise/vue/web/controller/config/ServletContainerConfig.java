package org.lmmarise.vue.web.controller.config;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/10 6:36 下午
 */
@Configuration
public class ServletContainerConfig {

    @Component
    static class CustomizationBean implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {
        @Override
        public void customize(UndertowServletWebServerFactory factory) {
            factory.addDeploymentInfoCustomizers(deploymentInfo -> {
                WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
                webSocketDeploymentInfo.setBuffers(new DefaultByteBufferPool(false, 1024));
                deploymentInfo.addServletContextAttribute("io.undertow.websockets.jsr.WebSocketDeploymentInfo",
                        webSocketDeploymentInfo);
            });
        }
    }
}
